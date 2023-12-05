package com.example.soularchive.model.repository

import com.example.soularchive.data.Artist
import com.example.soularchive.model.References
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

object AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore by lazy {
        Firebase.firestore
    }

    fun getUser(): String? {
        return auth.uid
    }

    fun login(email: String, password: String,
              onSuccess: (AuthResult) -> Unit,
              onFailure: (Exception) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener(onSuccess)
            .addOnFailureListener(onFailure)
    }

    fun register(
        artist: Artist, email: String, password: String,
        onResult: (Void?,Exception?) -> Unit,
    ){
        auth.createUserWithEmailAndPassword(email,password)//Register
            .addOnSuccessListener{newUser->
                artist.id = newUser.user?.uid.toString()
                db.collection(References.ARTIST).document(newUser.user!!.uid)
                    .set(artist)
                    .addOnSuccessListener{
                        onResult(it,null)
                    }
                    .addOnFailureListener{
                        onResult(null,it)
                    }
            }
            .addOnFailureListener{
                onResult(null,it)
            }
    }

    fun logout() {
        auth.signOut()
    }
}