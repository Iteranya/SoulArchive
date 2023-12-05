package com.example.soularchive.model.repository

import com.example.soularchive.data.Post
import com.example.soularchive.model.References
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

object UploadRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val galleryRef = db.collection(References.POST)
    private val artistRef = db.collection(References.ARTIST)

    fun uploadPost(post:Post,
               onSuccess: (DocumentReference) -> Unit,
               onFailure: (Exception) -> Unit){
        try{
            galleryRef.add(post)
                .addOnSuccessListener(onSuccess)
                .addOnFailureListener(onFailure)
        }catch (e:Exception){
            onFailure(e)
        }
    }
}