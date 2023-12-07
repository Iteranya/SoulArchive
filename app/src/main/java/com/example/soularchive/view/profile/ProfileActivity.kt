package com.example.soularchive.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.R
import com.example.soularchive.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artistId = intent.getStringExtra(ARTIST_ID)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.getArtistData(artistId!!)


        setContentView(binding.root)
    }

    companion object{
        const val ARTIST_ID = "artist"
    }
}