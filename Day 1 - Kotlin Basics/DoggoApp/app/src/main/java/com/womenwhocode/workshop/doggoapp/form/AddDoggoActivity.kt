package com.womenwhocode.workshop.doggoapp.form

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

class AddDoggoActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE = 1

    private val viewModel: ShowDoggoViewModel by lazy {
        ViewModelProviders.of(this).get(ShowDoggoViewModel::class.java)
    }


    lateinit var name : EditText
    lateinit var age : EditText
    lateinit var size :Spinner
    lateinit var photo : ImageView
    lateinit var cameraButton : Button
    lateinit var saveButton : Button
    lateinit var imageBitmap : Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_doggo)
        name = findViewById(R.id.name_text)
        age = findViewById(R.id.age_text)
        size = findViewById(R.id.size_spinner)
        photo = findViewById(R.id.photo_image)
        cameraButton =findViewById(R.id.photo_button)
        cameraButton.setOnClickListener { takePhoto() }
        saveButton =findViewById(R.id.save_dog)
        saveButton.setOnClickListener { saveDog() }
    }

    private fun saveDog() {
        if (name.text.isEmpty()) {
            name.error = "Doggo name plz"
       }
        if (age.text.isEmpty()) {
            age.error = "Doggo age plz"
    }
        displayDogInfo(name.text.toString(),age.text.toString(), size.selectedItem.toString(),imageBitmap)
    }

    private fun displayDogInfo(name: String, age: String, size: String, photo: Bitmap?) {
        viewModel.insert(Doggo(name,age,size,"https://boygeniusreport.files.wordpress.com/2016/11/puppy-dog.jpg?quality=98&strip=all&w=782"))
        onBackPressed()
    }

    private fun takePhoto(){
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
