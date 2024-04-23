package com.chansendev.fitchecksocialmedia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chansendev.fitchecksocialmedia.Models.User
import com.chansendev.fitchecksocialmedia.databinding.ActivityRegisterBinding
import com.chansendev.fitchecksocialmedia.utils.USER_NODE
import com.chansendev.fitchecksocialmedia.utils.USER_PROFILE_FOLDER
import com.chansendev.fitchecksocialmedia.utils.uploadImage
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class RegisterActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    lateinit var user: User
    private val launcher= registerForActivityResult(ActivityResultContracts.GetContent()){
        uri->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER){
                if(it==null){

                }else{
                    user.image=it
                    binding.addProfilePicture.setImageURI(uri)
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        user=User()
        binding.submitButton.setOnClickListener {
            if (binding.newName.editText?.text.toString().equals("") or
                binding.newEmail.editText?.text.toString().equals("") or
                binding.newPassword.editText?.text.toString().equals("")) {
                Toast.makeText(this@RegisterActivity,
                    "Please fill in all required information", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.newEmail.editText?.text.toString(),
                    binding.newPassword.editText?.text.toString()
                ).addOnCompleteListener {
                    result->

                    if (result.isSuccessful) {

                        user.name=binding.newName.editText?.text.toString()
                        user.email=binding.newEmail.editText?.text.toString()
                        user.password=binding.newPassword.editText?.text.toString()
                        Firebase.firestore.collection(USER_NODE)
                            .document(Firebase.auth.currentUser!!.uid).set(user)
                            .addOnSuccessListener {
                                Toast.makeText(this@RegisterActivity,
                                    "Registered Successfully", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@RegisterActivity,HomeActivity::class.java))
                                finish()
                            }
                    }else{
                        Toast.makeText(this@RegisterActivity,
                            result.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        binding.addProfilePicture.setOnClickListener {
            launcher.launch("image/&")
        }
    }
}