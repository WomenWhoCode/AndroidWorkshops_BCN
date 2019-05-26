package com.womenwhocode.workshop.doggoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DoggosAdapter(val doggos: List<Doggo>) : RecyclerView.Adapter<DoggoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoggoViewHolder =
        DoggoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_doggo_item, parent, false))

    override fun getItemCount(): Int = doggos.size

    override fun onBindViewHolder(holder: DoggoViewHolder, position: Int) {
        holder.bindDoggo(doggos[position])
    }
}

class DoggoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val image = view.findViewById<ImageView>(R.id.image)
    private val name = view.findViewById<TextView>(R.id.name)
    private val age = view.findViewById<TextView>(R.id.age)
    private val size = view.findViewById<TextView>(R.id.size)

    fun bindDoggo(doggo: Doggo) {
        image.setImageResource(doggo.image)
        name.text = doggo.name
        age.text = doggo.age
        size.text = doggo.size
    }
}

