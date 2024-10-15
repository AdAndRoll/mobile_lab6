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
    }
}
