package com.example.soularchive.view.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.soularchive.R
import com.example.soularchive.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {
    private lateinit var binding : FragmentGalleryBinding
    private lateinit var galleryViewModel : GalleryViewModel
    private lateinit var adapter : GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGalleryBinding.inflate(layoutInflater,container,false)

        galleryViewModel = ViewModelProvider(requireActivity())[GalleryViewModel::class.java]

        galleryViewModel.loading.observe(requireActivity()){
            //Loading :)
        }
        galleryViewModel.gallery.observe(requireActivity()) { posts ->
            adapter = GalleryAdapter(posts) { post ->
                galleryViewModel.currentPost.value = post
                findNavController().navigate(R.id.imageFullFragment)
            }
        }
        return binding.root
    }

}