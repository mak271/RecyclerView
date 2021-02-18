package com.example.recyclerview.ui.Photo

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.example.recyclerview.R
import com.squareup.picasso.Picasso


class Photo: Fragment() {

    private val REQUEST_TAKE_PHOTO = 1
    private val IMAGE_PICK_CODE = 2
    private lateinit var img: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.photo_main, container, false)

        img = root.findViewById(R.id.image_view)
        root.findViewById<Button>(R.id.btn_make).setOnClickListener {
            if (checkCameraPermission()) takePicture()
        }

        root.findViewById<Button>(R.id.btn_choice).setOnClickListener {
            if (checkStoragePermission()) pickImageFromGallery()
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK)
            img.setImageBitmap(data?.extras?.get("data") as Bitmap)

        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK)
            img.setImageURI(data?.data)
    }

    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        try {
            startActivityForResult(intent, IMAGE_PICK_CODE)
        } catch (exc: ActivityNotFoundException) {
            println(exc.message.toString())
        }
    }

    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        } catch (exc: ActivityNotFoundException) {
            println(exc.message.toString())
        }
    }



    // Проверка разрешения доступа к камере устройства
    private fun checkCameraPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 1111)
            }
        }
        return true
    }

    // Проверка доступа к хранилищу данных
    private fun checkStoragePermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1111)
            }
        }
        return true
    }



}





















