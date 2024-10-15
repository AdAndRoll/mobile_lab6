package com.example.lab_6_1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_6.RectangleAdapter


class Task2Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RectangleAdapter
    private val items = mutableListOf<RectangleItem>()
    private var currentId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RectangleAdapter(items) { position ->
            items.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        recyclerView.adapter = adapter

        val addButton = findViewById<Button>(R.id.addButton)
        addButton.setOnClickListener {
            addNewItem()
        }
    }

    private fun addNewItem() {
        items.add(RectangleItem(currentId, "Прямоугольник $currentId"))
        currentId++
        adapter.notifyItemInserted(items.size - 1)
    }
}
