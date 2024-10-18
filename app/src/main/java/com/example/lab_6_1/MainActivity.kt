package com.example.lab_6_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val task1But = findViewById<Button>(R.id.task1But)
        task1But.setOnClickListener{
            val intent = Intent(this, Task1Activity::class.java)
            startActivity(intent)
        }
        val task2But = findViewById<Button>(R.id.task2But)
        task2But.setOnClickListener{
            val intent = Intent(this, Task2Activity::class.java)
            startActivity(intent)
        }
        val task3But = findViewById<Button>(R.id.task3But)
        task3But.setOnClickListener{
            val intent = Intent(this, Task3Activity::class.java)
            startActivity(intent)
        }
        val task4But = findViewById<Button>(R.id.forTaskBut)
        task4But.setOnClickListener{
            val intent = Intent(this, Task4Activity::class.java)
            startActivity(intent)
        }
        val task5But = findViewById<Button>(R.id.task5But)
        task5But.setOnClickListener {
            val intent = Intent(this, Task5Activity::class.java)
            startActivity(intent)
        }
        val task6But = findViewById<Button>(R.id.task6But)
        task6But.setOnClickListener {
            val intent = Intent(this, Task6Activity::class.java)
            startActivity(intent)
        }
        val task7But = findViewById<Button>(R.id.task7But)
        task7But.setOnClickListener {
            val intent = Intent(this, Task7Activity::class.java)
            startActivity(intent)
        }
        val task8But = findViewById<Button>(R.id.task8But)
        task8But.setOnClickListener {
            val intent = Intent(this, Task8Activity::class.java)
            startActivity(intent)
        }

        }
}
