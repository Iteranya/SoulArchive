package com.example.soularchive.model.repository

import com.example.soularchive.data.Post
import com.example.soularchive.model.References
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
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

    val galleryRef = db.collection(References.POST)
    fun getUser(): String? {
        return auth.uid
    }

    fun getStories( //But with ✨✨ Paging ✨✨ (I have no idea how to do it)
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
}