package com.example.soularchive.view.upload

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soularchive.data.Post
import com.example.soularchive.model.repository.AuthRepository
import com.example.soularchive.model.repository.UploadRepository
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UploadViewModel: ViewModel() {

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _imageBitmap = MutableLiveData<Bitmap?>()
    val imageBitmap: LiveData<Bitmap?> = _imageBitmap

    private fun getCurrentDateAsString(format: String = "yyyy-MM-dd-mm-ss"): String {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(Date())
    }


    fun upload(title:String,desc:String){
        viewModelScope.launch {
            try {
                val image = imageBitmap.value
                val date = getCurrentDateAsString()
                val user = AuthRepository.getUser().toString()
                if(image == null)
                    throw(Exception("Please Add Media"))
                val media = UploadRepository.uploadImage(
                    image,
                    title + date,
                    user
                )

                val post = Post(
                    title = title,
                    artistId = user,
                    description = desc,
                    media = media,
                    verified = true,
                    uploaded = Date().time.toInt()
                )
                UploadRepository.uploadPost(post,
                    onSuccess = {
                        _message.value = "Upload Success"
                    },
                    onFailure = {
                        _message.value = "Upload Failed"
                    }
                )
            }catch (e:Exception){
                _message.value = e.message
            }

        }
    }

    fun storeImage(bitmap: Bitmap, quality: Int) {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        val compressedBitmap =
            BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size())
        _imageBitmap.value = compressedBitmap
    }
}