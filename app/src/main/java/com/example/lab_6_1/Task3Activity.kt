package com.example.lab_6_1

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Task3Activity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RectangleAdapterFor3Task
    private val rectangles = mutableListOf<Rectangle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3)

        recyclerView = findViewById(R.id.recyclerView)
        val addButton: FloatingActionButton = findViewById(R.id.addButton)

        // Настройка RecyclerView
        adapter = RectangleAdapterFor3Task(rectangles) { position ->
            // Обработка нажатия на элемент списка
            rectangles.removeAt(position) // Удаляем элемент
            adapter.notifyItemRemoved(position) // Уведомляем адаптер об изменении
            adapter.notifyItemRangeChanged(position, rectangles.size) // Обновляем порядковые номера
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Обработка нажатия на кнопку добавления
        addButton.setOnClickListener {
            val newId = rectangles.size + 1 // Создаем новый ID
            val backgroundColor = Color.rgb((0..255).random(), (0..255).random(), (0..255).random()) // Случайный цвет фона
            val textColor = Color.WHITE // Цвет текста
            rectangles.add(Rectangle(newId, backgroundColor, textColor)) // Добавляем новый прямоугольник
            adapter.notifyItemInserted(rectangles.size - 1) // Уведомляем адаптер об изменении
        }
    }
}
