package com.example.lab_6

import com.example.lab_6_1.R
import com.example.lab_6_1.RectangleItem


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RectangleAdapter(private val items: MutableList<RectangleItem>, private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<RectangleAdapter.RectangleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RectangleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rectangle, parent, false)
        return RectangleViewHolder(view)
    }

    override fun onBindViewHolder(holder: RectangleViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, onItemClick)
    }

    override fun getItemCount() = items.size

    class RectangleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemText: TextView = itemView.findViewById(R.id.itemText)

        fun bind(item: RectangleItem, onItemClick: (Int) -> Unit) {
            itemText.text = "${item.id}. ${item.text}"
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }
}
