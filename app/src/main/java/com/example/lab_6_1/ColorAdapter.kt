package com.example.lab_6_1

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(private val context: Context, private val items: List<ColorItem>) :
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_color, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val colorName: TextView = itemView.findViewById(R.id.colorName)

        fun bind(item: ColorItem) {
            colorName.text = item.colorName
            itemView.setBackgroundColor(Color.parseColor(item.colorHex))
            colorName.setTextColor(Color.WHITE) // Текст всегда белый
        }
    }
}
