package com.example.soularchive.view.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.R
import com.example.soularchive.databinding.FragmentImageFullBinding


class ImageFullFragment : Fragment() {
    private lateinit var binding : FragmentImageFullBinding
    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        galleryViewModel = ViewModelProvider(requireActivity())[GalleryViewModel::class.java]
        binding = FragmentImageFullBinding.inflate(layoutInflater,container,false)

        galleryViewModel.currentPost.observe(requireActivity()){
            //Insert da images here, humu humu~
        }
        return binding.root
    }

}