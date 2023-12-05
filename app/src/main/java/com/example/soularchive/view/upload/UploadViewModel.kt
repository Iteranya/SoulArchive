package com.example.soularchive.view.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.soularchive.data.Post
import com.example.soularchive.model.repository.UploadRepository

class UploadViewModel: ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun upload(post: Post){
        UploadRepository.uploadPost(post,
            onSuccess = {
                _message.value = "Upload Success"
            },
            onFailure = {
                _message.value = "Upload Failed"
            }

        )
    }
}