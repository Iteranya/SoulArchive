package com.example.soularchive.view.upload

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.R
import com.example.soularchive.databinding.FragmentUploadBinding


class VerificationFragment : Fragment() {
    private lateinit var binding : FragmentUploadBinding
    private lateinit var viewModel: UploadViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[UploadViewModel::class.java]

        return binding.root
    }

}