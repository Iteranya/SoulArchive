package com.example.soularchive.view.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.soularchive.databinding.FragmentArtistBinding
import com.example.soularchive.view.gallery.GalleryAdapter


class ArtistFragment : Fragment() {
    private lateinit var binding : FragmentArtistBinding
    private lateinit var viewModel: ArtistViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[ArtistViewModel::class.java]
        binding = FragmentArtistBinding.inflate(layoutInflater,container,false)

        viewModel.artist.observe(requireActivity()){
            Glide.with(binding.imgArtistBanner).load(it.banner).into(binding.imgArtistBanner)
            Glide.with(binding.imgArtistPfp).load(it.photo).into(binding.imgArtistPfp)
            binding.tvDescArtist.text = it.description
            binding.tvNameArtist.text = it.username
        }

        viewModel.artistGallery.observe(requireActivity()){
            binding.recyclerView.layoutManager = GridLayoutManager(requireActivity(),4)
            binding.recyclerView.adapter = GalleryAdapter(it){
                //navigate to full
            }
        }

        return binding.root
    }


}