package com.coffeearmy.reciclerview

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 *  :3
 */
class WordListAdapter(private val mWordList: LinkedList<String>, context: Context) :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {



    private var mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordViewHolder {
        val mItemView = mInflater.inflate(R.layout.item_word, p0, false)
        return WordViewHolder(mItemView, this)
    }

    override fun getItemCount(): Int {
        return mWordList.size//To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(p0: WordViewHolder, p1: Int) {
        val mCurrent = mWordList[p1]
        p0.wordItemView.text = mCurrent
    }


    inner class WordViewHolder(itemView: View, val mAdapter: WordListAdapter) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            // Get the position of the item that was clicked.
            val mPosition = layoutPosition

// Use that to access the affected item in mWordList.
            val element = mWordList[mPosition]
// Change the word in the mWordList.

            mWordList[mPosition] = "Clicked! $element"
// Notify the adapter, that the data has changed so it can
// update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged()

        }

        val wordItemView: TextView = itemView.findViewById(R.id.word)

    }
}