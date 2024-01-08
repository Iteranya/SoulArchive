package com.example.soularchive.view.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.databinding.FragmentCreationBinding

class CreationFragment : Fragment() {
    private lateinit var binding : FragmentCreationBinding
    private lateinit var viewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreationBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        return binding.root
    }

}