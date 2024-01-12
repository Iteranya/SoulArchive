package com.example.soularchive.view.gallery

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.soularchive.R
import com.example.soularchive.databinding.ActivityGalleryBinding
import com.example.soularchive.view.upload.UploadActivity

class GalleryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGalleryBinding
    private lateinit var galleryViewModel: GalleryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        galleryViewModel = ViewModelProvider(this)[GalleryViewModel::class.java]

        galleryViewModel.getPosts()

        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_gallery -> {
                    // Kalo klik home (masih gatau mau naro apa di halaman home)
                    Navigation.findNavController(
                        this, binding.fragmentContainerView.id
                    ).navigate(R.id.galleryFragment)
                    true
                }
                R.id.menu_upload -> {
                    //Nyalain kamera/storage
                    startActivity(Intent(this, UploadActivity::class.java))
                    true
                }
                R.id.menu_favorite -> {
                    Navigation.findNavController(
                        this, binding.fragmentContainerView.id
                    ).navigate(R.id.collectionFragment)
                    true
                }
                R.id.menu_message -> {
                    Toast.makeText(this,"Under Construction",Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

    }


}