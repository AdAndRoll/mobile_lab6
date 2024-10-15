package com.example.lab_6_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Task1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val colors = listOf(
            ColorItem("Red", "#FF0000"),
            ColorItem("Green", "#00FF00"),
            ColorItem("Blue", "#0000FF")
        )

        val adapter = ColorAdapter(this, colors)
        recyclerView.adapter = adapter
    }
}
