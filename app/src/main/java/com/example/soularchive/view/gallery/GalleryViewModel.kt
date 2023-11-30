package com.example.soularchive.view.gallery

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

class GalleryViewModel : ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _gallery = MutableLiveData<List<Post>>()
    val gallery: LiveData<List<Post>> = _gallery

    val currentPost = MutableLiveData<Post>()
    val currentArtist = MutableLiveData<Artist>()

    val loading = MutableLiveData<Boolean>()

    fun getPosts(){
        viewModelScope.launch {
            loading.value = true
            GalleryRepository.getPosts {
                if(it.isSuccess && it.getOrNull()!=null){
                    _gallery.value = it.getOrNull()!!.toObjects()
                }

                loading.value = false
            }
        }
    }

    fun getPosts(sort:Sort){
        viewModelScope.launch {
            loading.value = true
            GalleryRepository.getPosts(sort.name) {
                if(it.isSuccess && it.getOrNull()!=null){
                    _gallery.value = it.getOrNull()!!.toObjects()
                }
                loading.value = false
            }
        }
    }

    fun getArtist(id:String){
        viewModelScope.launch {
            GalleryRepository.getArtist(id){
                if(it.isSuccess && it.getOrNull()!=null){
                    currentArtist.value = it.getOrNull()!!.toObject()
                }
                loading.value = false
            }
        }
    }

    enum class Sort{
        favorite, likes, uploaded
    }

}