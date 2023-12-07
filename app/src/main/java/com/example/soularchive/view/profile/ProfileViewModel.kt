package com.example.soularchive.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soularchive.data.Artist
import com.example.soularchive.data.Post
import com.example.soularchive.model.repository.GalleryRepository
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _artist = MutableLiveData<Artist>()
    val artist: LiveData<Artist> = _artist

    private val _artistGallery = MutableLiveData<List<Post>>()
    val artistGallery: LiveData<List<Post>> = _artistGallery
    fun getArtistData(id:String) {
        viewModelScope.launch {
            try{
                GalleryRepository.getArtist(id){
                    val result = it.getOrThrow()
                    _artist.value = result.toObject()
                }
            }catch (e:Exception){
                _message.value = e.message
            }

        }

    }

    fun getArtistCreation(id:String){
        viewModelScope.launch{
            try{
                GalleryRepository.getPostArtist(id){
                    _artistGallery.value = it.getOrThrow().toObjects()
                    _message.value = "Success"
                }
            }catch (e:Exception){
                _message.value = e.message
            }
        }
    }

}