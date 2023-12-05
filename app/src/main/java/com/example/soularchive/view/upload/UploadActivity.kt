package com.example.soularchive.view.upload

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.soularchive.R
import com.example.soularchive.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUploadBinding
    private lateinit var viewModel: UploadViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[UploadViewModel::class.java]
        viewModel.message.observe(this){
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
        }
    }
}
