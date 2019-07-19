package com.womenwhocode.workshop.doggoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.womenwhocode.workshop.doggoapp.list.DoggosActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * View HugeDogActivity
     */
    fun viewHugeDog(v: View) {
        startActivity(Intent(this, HugeDogActivity::class.java))
    }

    fun viewTinyDog(v: View) {
        startActivity(Intent(this, TinyDogActivity::class.java))
    }

    fun addDoggo(v: View) {
        startActivity(Intent(this, AddDoggoActivity::class.java))
    }

    fun seeMoreDoggos(v: View) {
        startActivity(Intent(this, DoggosActivity::class.java))
    }
}
