package com.example.soularchive.view.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.R
import com.example.soularchive.databinding.ActivityGalleryBinding

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGalleryBinding
    private lateinit var galleryViewModel: GalleryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        galleryViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]

        galleryViewModel.getPosts()

        setContentView(binding.root)

    }
}