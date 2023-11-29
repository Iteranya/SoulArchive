package com.example.soularchive.view.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.R
import com.example.soularchive.data.Artist
import com.example.soularchive.databinding.FragmentLoginBinding
import com.example.soularchive.databinding.FragmentRegisterBinding
import com.example.soularchive.view.auth.AuthViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding : FragmentRegisterBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        binding = FragmentRegisterBinding.inflate(layoutInflater,container,false)

        binding.btnRegister.setOnClickListener {
            val name  = binding.edUsernameRegister.text
            val email = binding.edEmailRegister.text
            val password = binding.edPasswordRegister.text
            if(email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()){
                authViewModel.register(email.toString(),password.toString(), Artist(username = name.toString()))
            }else{
                Toast.makeText(requireActivity(),"Please Fill Everything~", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }


}