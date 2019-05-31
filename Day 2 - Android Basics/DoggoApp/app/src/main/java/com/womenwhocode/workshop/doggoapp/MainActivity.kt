package com.womenwhocode.workshop.doggoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

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
}
