package com.example.soularchive.view.artist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.databinding.ActivityProfileBinding

class ArtistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    private lateinit var viewModel: ArtistViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val artistId = intent.getStringExtra(ARTIST_ID)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[ArtistViewModel::class.java]
        viewModel.getArtistData(artistId!!)

        setContentView(binding.root)
    }

    companion object{
        const val ARTIST_ID = "artist"
    }
}