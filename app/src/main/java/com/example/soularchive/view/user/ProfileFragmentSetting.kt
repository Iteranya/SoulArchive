package com.example.soularchive.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.databinding.FragmentProfileSettingBinding

class ProfileFragmentSetting : Fragment() {
    private lateinit var binding : FragmentProfileSettingBinding
    private lateinit var viewModel : UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileSettingBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        return binding.root
    }
}