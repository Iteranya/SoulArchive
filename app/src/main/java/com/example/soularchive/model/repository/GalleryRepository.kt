package com.example.soularchive.model.repository

import com.example.soularchive.data.Artist
import com.example.soularchive.data.Collection
import com.example.soularchive.data.Post
import com.example.soularchive.model.References
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

object GalleryRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    private val galleryRef = db.collection(References.POST)
    val artistRef = db.collection(References.ARTIST)
    private fun getUser(): String? {
        return auth.uid
    }

    fun getPostsPaging( //But with ✨✨ Paging ✨✨ (I have no idea how to do it)
        last:(QuerySnapshot) -> Unit,
        nextPage: (QuerySnapshot?) -> Unit
    ){
        try {
            val first = galleryRef.limit(25)
            first.get()
                .addOnSuccessListener { documentSnapshots ->
                    val lastVisible = documentSnapshots.documents[documentSnapshots.size() - 1]

                    val next = galleryRef
                        .startAfter(lastVisible)
                        .limit(25)
                }

        }catch (e:Exception){
            //Do Nothing
        }
    }

    suspend fun getPosts(
        posts:(Result<QuerySnapshot>) -> Unit
    ){//Lambda Statement Go BRRRRRRR~
        try{
            val gallery = galleryRef.orderBy("upload").get().await()
            posts(Result.success(gallery))
        }catch (e:Exception){
            posts(Result.failure(e))
        }

    }

    suspend fun getPosts(
        sortBy : String,
        posts:(Result<QuerySnapshot>)-> Unit
    ){
        try{
            val gallery = galleryRef.orderBy(sortBy).get().await()
            posts(Result.success(gallery))
        }catch (e:Exception){
            posts(Result.failure(e))
        }

    }

    suspend fun getPostArtist(
        id:String,
        posts:(Result<QuerySnapshot>)-> Unit
    ){
        try{
            val gallery = galleryRef.whereEqualTo("artistId",id).get().await()
            posts(Result.success(gallery))
        }catch (e:Exception){
            posts(Result.failure(e))
        }
    }
    suspend fun getArtist(
        id: String,
        artist:(Result<DocumentSnapshot>)-> Unit
    ){
        try{
            val data = artistRef.document(id).get().await()
            artist(Result.success(data))
        }catch (e:Exception){
            artist(Result.failure(e))
        }

    }

    suspend fun addCollection(
        postId:String,
        imageId: String
    ): Result<String> {//Redundancy? More like, optimization 😎
        return try{
            artistRef.document(getUser()!!).collection("collection").document(postId).set(Collection(postId,imageId)).await()
            Result.success("Successful")
        }catch (e:Exception){
            Result.failure(e)
        }
    }

    suspend fun deleteCollection(
        postId:String
    ):Result<String>{
        return try{
            artistRef.document(getUser()!!).collection("collection").document(postId).delete().await()
            Result.success("Successful")
        }catch (e:Exception){
            Result.failure(e)
        }
    }

}