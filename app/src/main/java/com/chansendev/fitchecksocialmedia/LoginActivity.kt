package com.chansendev.fitchecksocialmedia

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chansendev.fitchecksocialmedia.Models.User
import com.chansendev.fitchecksocialmedia.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.loginButton.setOnClickListener {
            if(binding.loginEmail.editText?.text.toString().equals("") or
                binding.loginPassword.editText?.text.toString().equals("")){
                Toast.makeText(this@LoginActivity,"Please enter all information",
                    Toast.LENGTH_SHORT).show()
            }else{
                var user= User(binding.loginEmail.editText?.text.toString(),
                    binding.loginPassword.editText?.text.toString())

                Firebase.auth.signInWithEmailAndPassword(user.email!!, user.password!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                        }else{
                            Toast.makeText(this@LoginActivity, it.exception
                                ?.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}