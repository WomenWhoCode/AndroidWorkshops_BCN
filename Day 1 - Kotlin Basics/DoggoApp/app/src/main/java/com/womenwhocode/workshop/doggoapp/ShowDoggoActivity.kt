package com.womenwhocode.workshop.doggoapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

const val NAME_FIELD: String = "NAME"
const val AGE_FIELD: String = "AGE"
const val SIZE_FIELD: String = "SIZE"
const val PHOTO_FIELD: String = "PHOTO"


class ShowDoggoActivity : AppCompatActivity() {

    lateinit var name : TextView
    lateinit var age : TextView
    lateinit var size :TextView
    lateinit var photo : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_doggo)
        name = findViewById(R.id.name_show_text)
        age = findViewById(R.id.age_show_text)
        size = findViewById(R.id.size_show_text)
        photo = findViewById(R.id.photo_image)
        getDogInfo(intent.extras)
    }

    private fun getDogInfo(extras: Bundle?) {
        name.text = extras?.getString(NAME_FIELD)
        age.text = extras?.getString(AGE_FIELD)
        size.text = extras?.getString(SIZE_FIELD)
        photo.setImageBitmap(extras?.getParcelable(PHOTO_FIELD))
    }


}
