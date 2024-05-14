package com.chansendev.fitchecksocialmedia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chansendev.fitchecksocialmedia.R
import com.chansendev.fitchecksocialmedia.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding :FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}