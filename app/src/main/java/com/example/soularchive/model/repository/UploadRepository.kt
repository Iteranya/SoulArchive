package com.example.soularchive.model.repository

import android.graphics.Bitmap
import android.util.Log
import com.example.soularchive.data.Post
import com.example.soularchive.model.References
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

object UploadRepository {
    private val auth = FirebaseAuth.getInstance()
    private val storage = Firebase.storage
    private val storageRef = storage.reference
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val galleryRef = db.collection(References.POST)
    private val artistRef = db.collection(References.ARTIST)

    suspend fun uploadPost(post:Post,
               onSuccess: (Void) -> Unit,
               onFailure: (Exception) -> Unit){
        try{
            val newPost = galleryRef.add(post).await()
            val id = newPost.id
            Log.i("Mitochondria", newPost.id)
            galleryRef.document(id).update("id",id)
                .addOnSuccessListener{
                    onSuccess(it)
                }.addOnFailureListener {
                    onFailure(it)
                }
        }catch (e:Exception){
            onFailure(e)
        }
    }

    suspend fun uploadImage(image: Bitmap, fileName: String, userId: String): String {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val path = "UserData/$userId/Artwork/$fileName.jpg"
        val imageRef = storageRef.child(path)
        return try {
            imageRef.putBytes(data).await()
            val downloadUrl = imageRef.downloadUrl.await()
            downloadUrl.toString()
        } catch (e: Exception) {
            throw e
        }
    }
}