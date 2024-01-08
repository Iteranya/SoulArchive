package com.example.soularchive.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.databinding.FragmentUploadBinding

class CommissionSettingFragment : Fragment() {
    private lateinit var binding : FragmentUploadBinding
    private lateinit var viewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentUploadBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        return binding.root
    }

}