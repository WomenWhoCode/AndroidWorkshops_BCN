package com.womenwhocode.workshop.doggoapp.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        val doggosAdapter = DoggosAdapter()
        recyclerView.adapter = doggosAdapter
        viewModel.getDoggos().observe(this,
            Observer<List<Doggo>> { dogs -> dogs?.let { doggosAdapter.displayDoggos(it) } })
    }
}