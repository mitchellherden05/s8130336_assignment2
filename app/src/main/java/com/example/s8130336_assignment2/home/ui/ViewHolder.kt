package com.example.s8130336_assignment2.home.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textView: TextView = view.findViewById(R.id.textView)

    fun bind(item: String) {
        textView.text = item

    }
}