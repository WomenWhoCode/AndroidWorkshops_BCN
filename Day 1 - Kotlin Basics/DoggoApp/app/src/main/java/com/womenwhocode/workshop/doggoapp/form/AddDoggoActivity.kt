package com.womenwhocode.workshop.doggoapp.form

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.womenwhocode.workshop.doggoapp.*
import com.womenwhocode.workshop.doggoapp.list.DoggoViewModel
import com.womenwhocode.workshop.doggoapp.list.DoggosActivity.Companion.AGE_FIELD
import com.womenwhocode.workshop.doggoapp.list.DoggosActivity.Companion.NAME_FIELD
import com.womenwhocode.workshop.doggoapp.list.DoggosActivity.Companion.SIZE_FIELD

class AddDoggoActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    lateinit var name: EditText
    lateinit var age: EditText
    lateinit var size: Spinner
    lateinit var photo: ImageView
    lateinit var cameraButton: Button
    lateinit var saveButton: Button
    lateinit var imageBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_doggo)
        name = findViewById(R.id.name_text)
        age = findViewById(R.id.age_text)
        size = findViewById(R.id.size_spinner)
        photo = findViewById(R.id.photo_image)
        cameraButton = findViewById(R.id.photo_button)
        cameraButton.setOnClickListener { takePhoto() }
        saveButton = findViewById(R.id.save_dog)
        saveButton.setOnClickListener { saveDog() }
    }

    private fun saveDog() {
        if (name.text.isEmpty()) {
            name.error = "Doggo name plz"
        }
        if (age.text.isEmpty()) {
            age.error = "Doggo age plz"
        }
        displayDogInfo(name.text.toString(), age.text.toString(), size.selectedItem.toString())
    }

    private fun displayDogInfo(name: String, age: String, size: String) {
        var intent = Intent()
        intent.putExtra(NAME_FIELD, name)
        intent.putExtra(AGE_FIELD, age)
        intent.putExtra(SIZE_FIELD, size)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            imageBitmap = data?.extras?.get("data") as Bitmap
            photo.setImageBitmap(imageBitmap)
        }
    }

}
