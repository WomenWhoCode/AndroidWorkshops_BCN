package com.womenwhocode.workshop.doggoapp.form

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.R

const val NAME_FIELD: String = "NAME"
const val AGE_FIELD: String = "AGE"
const val SIZE_FIELD: String = "SIZE"
const val PHOTO_FIELD: String = "PHOTO"


class ShowDoggoActivity : AppCompatActivity() {


    lateinit var name: TextView
    lateinit var age: TextView
    lateinit var size: TextView
    lateinit var photo: ImageView


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
        val nameExtra = extras?.getString(NAME_FIELD)
        val ageExtra = extras?.getString(AGE_FIELD)
        val sizeExtra = extras?.getString(SIZE_FIELD)

        name.text = nameExtra
        age.text = ageExtra
        size.text = sizeExtra
        photo.setImageBitmap(extras?.getParcelable(PHOTO_FIELD))

     //   viewModel.insert(Doggo(nameExtra!!,ageExtra!!,sizeExtra!!,"https://boygeniusreport.files.wordpress.com/2016/11/puppy-dog.jpg?quality=98&strip=all&w=782"))
    }

}
