package com.example.soularchive.view.gallery

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
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
        binding.rvGallery.layoutManager = GridLayoutManager(requireActivity(),2)
        galleryViewModel = ViewModelProvider(requireActivity())[GalleryViewModel::class.java]

        galleryViewModel.loading.observe(requireActivity()){
            //Loading :)
        }
        galleryViewModel.gallery.observe(requireActivity()) { posts ->
            Log.i("mitochondria",posts[0].description.toString())
            adapter = GalleryAdapter(posts) { post ->
                galleryViewModel.currentPost.value = post
                findNavController().navigate(R.id.imageFullFragment)
            }

            binding.rvGallery.adapter = adapter
        }
        return binding.root
    }

}