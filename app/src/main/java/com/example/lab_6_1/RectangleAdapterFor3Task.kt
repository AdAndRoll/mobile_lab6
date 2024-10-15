package com.example.lab_6_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RectangleAdapterFor3Task(
    private var rectangles: List<Rectangle>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<RectangleAdapterFor3Task.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.rectangleText)

        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rectangle_item_3task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rectangle = rectangles[position]
        holder.textView.text = "Прямоугольник #${rectangle.id}"
        holder.textView.setBackgroundColor(rectangle.backgroundColor)
        holder.textView.setTextColor(rectangle.textColor)
    }

    override fun getItemCount(): Int {
        return rectangles.size
    }
}
