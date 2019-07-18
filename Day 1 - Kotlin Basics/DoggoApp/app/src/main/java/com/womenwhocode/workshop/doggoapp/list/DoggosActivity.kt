package com.womenwhocode.workshop.doggoapp.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.womenwhocode.workshop.doggoapp.form.AddDoggoActivity
import com.womenwhocode.workshop.doggoapp.Doggo
import com.womenwhocode.workshop.doggoapp.R


class DoggosActivity : AppCompatActivity() {

    private val viewModel: DoggoViewModel by lazy {
        ViewModelProviders.of(this).get(DoggoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doggos)

        initRecyclerView()
        initFavButton()
    }

    private fun initFavButton() {
        val favButton = findViewById<FloatingActionButton>(R.id.floating_action_button)
        favButton.setOnClickListener {
            startActivityForResult(
                Intent(this, AddDoggoActivity::class.java),
                NEW_DOGGO_CODE
            )
        }
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        val doggosAdapter = DoggosAdapter()
        recyclerView.adapter = doggosAdapter
        viewModel.getDoggos().observe(this,
            Observer<List<Doggo>> { dogs ->
                dogs?.let {
                    doggosAdapter.displayDoggos(it)
                    Log.d("DoggoViewModelAcc", "Success: ${it.size} dogs retrieved from databae")
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_DOGGO_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val nameExtra = data.extras?.getString(NAME_FIELD)
                val ageExtra = data.extras?.getString(AGE_FIELD)
                val sizeExtra = data.extras?.getString(SIZE_FIELD)

                viewModel.insert(
                    Doggo(
                        nameExtra!!,
                        ageExtra!!,
                        sizeExtra!!,
                        "https://boygeniusreport.files.wordpress.com/2016/11/puppy-dog.jpg?quality=98&strip=all&w=782"
                    )
                )
            }
        }
    }

    companion object {
        const val NEW_DOGGO_CODE = 1
        const val NAME_FIELD: String = "NAME"
        const val AGE_FIELD: String = "AGE"
        const val SIZE_FIELD: String = "SIZE"
        const val PHOTO_FIELD: String = "PHOTO"

    }
}