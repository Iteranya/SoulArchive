package com.example.soularchive.view.upload

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.soularchive.R
import com.example.soularchive.databinding.FragmentUploadBinding

class UploadFragment : Fragment() {
    private lateinit var binding : FragmentUploadBinding
    private lateinit var viewModel: UploadViewModel
    val cameraPermission = android.Manifest.permission.CAMERA
    val storagePermission = android.Manifest.permission.READ_EXTERNAL_STORAGE
    val mediaPermission = android.Manifest.permission.ACCESS_MEDIA_LOCATION
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUploadBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[UploadViewModel::class.java]


        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap
                viewModel.storeImage(imageBitmap,100)
            }
        }
        val documentLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            // Handle the result of the file picker intent here
            // The selected image can be loaded from the URI using a ContentResolver
            val contentResolver = requireActivity().contentResolver
            val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
            viewModel.storeImage(imageBitmap, 100)
        }
        fun dispatchTakePictureIntent() {
            val pickImage = "Pick Image"
            val takePhoto = "Take Photo"

            val options = arrayOf<CharSequence>(pickImage, takePhoto)
            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle("Select Image Source")
            builder.setItems(options) { dialog, item ->
                when (options[item]) {
                    pickImage -> {
                        if (ContextCompat.checkSelfPermission(requireActivity(), storagePermission) != PackageManager.PERMISSION_GRANTED && (ContextCompat.checkSelfPermission(requireActivity(),mediaPermission)) != PackageManager.PERMISSION_GRANTED
                        ) {
                            ActivityCompat.requestPermissions(
                                requireActivity(),
                                arrayOf(storagePermission,mediaPermission),
                                4
                            )
                        } else {
                            // Permission granted, launch file picker intent
                            val intent = Intent(Intent.ACTION_GET_CONTENT)
                            intent.type = "image/*" // only allow image file types
                            documentLauncher.launch(intent.type)
                        }
                    }
                    takePhoto -> {
                        if (ContextCompat.checkSelfPermission(
                                requireActivity(),
                                cameraPermission
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            // Permission not granted, request it
                            ActivityCompat.requestPermissions(
                                requireActivity(),
                                arrayOf(cameraPermission),
                                6
                            )
                        } else {
                            // Permission granted, launch camera intent
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            resultLauncher.launch(intent)
                        }
                    }
                }
            }
            builder.show()
        }

        binding.btnUpload.setOnClickListener {
            dispatchTakePictureIntent()
            Glide.with(binding.imageView4).load(viewModel.imageBitmap.value).into(binding.imageView4)
        }

        binding.btnPublish.setOnClickListener {
            viewModel.upload(
                binding.edtitleUpload.text.toString(),
                binding.edDescUpload.text.toString()
            )
        }

        return binding.root
    }
    
}