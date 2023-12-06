package com.example.soularchive.view.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soularchive.data.Artist
import com.example.soularchive.data.Post
import com.example.soularchive.model.repository.GalleryRepository
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _artist = MutableLiveData<Artist>()
    val artist: LiveData<Artist> = _artist
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

}