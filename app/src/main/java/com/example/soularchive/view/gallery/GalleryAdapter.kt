package com.example.soularchive.view.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soularchive.data.Post
import com.example.soularchive.databinding.PhotoSmallItemBinding

class GalleryAdapter(private val posts : List<Post>, val post:(Post)->Unit):RecyclerView.Adapter<GalleryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = PhotoSmallItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        with(holder){
            with(binding){
                Glide.with(this.imageView2).load(posts[position].media).into(this.imageView2)
                this.imageView2.setOnClickListener {
                    post(posts[position])
                }
            }
        }
    }
}

class GalleryViewHolder(
    val binding: PhotoSmallItemBinding
): RecyclerView.ViewHolder(binding.root)