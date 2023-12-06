package com.example.soularchive.model.repository

import com.example.soularchive.data.Post
import com.example.soularchive.model.References
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects

object ProfileRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val galleryRef = db.collection(References.POST)

    private fun getUser(): String? {
        return auth.uid
    }

    fun getArtistArtwork(onStatusChanged: (List<Post>) -> Unit) {
        galleryRef.whereEqualTo("artistId", getUser()).addSnapshotListener { document, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            val documents = document!!.toObjects<Post>()
            onStatusChanged(documents)
        }
    }

    fun getArtistArtwork(artist:String, onStatusChanged: (List<Post>) -> Unit) {
        galleryRef.whereEqualTo("artistId", artist).addSnapshotListener { document, error ->
            if (error != null) {
                return@addSnapshotListener
            }
            val documents = document!!.toObjects<Post>()
            onStatusChanged(documents)
        }
    }
}