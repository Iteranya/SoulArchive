package com.example.soularchive.view.auth.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.R
import com.example.soularchive.databinding.FragmentLoginBinding
import com.example.soularchive.view.auth.AuthViewModel


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authViewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)

        binding.button.setOnClickListener {
            val email = binding.edEmailLogin.text
            val password = binding.edPasswordLogin.text
            if(email.isNotEmpty() && password.isNotEmpty()){
                authViewModel.login(email.toString(),password.toString())
            }else{
                Toast.makeText(requireActivity(),"Please Fill Everything~",Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }


}