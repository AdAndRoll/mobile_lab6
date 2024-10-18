package com.example.lab_6_1

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Task7Activity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextValue: EditText
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task7)

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        editTextValue = findViewById(R.id.editTextValue)
        checkBox = findViewById(R.id.checkBox)
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val buttonLoad: Button = findViewById(R.id.buttonLoad)

        // Загрузка сохраненных данных при старте приложения
        loadSavedData()

        // Обработчик кнопки сохранения
        buttonSave.setOnClickListener {
            saveData()
        }

        // Обработчик кнопки загрузки
        buttonLoad.setOnClickListener {
            loadSavedData()
        }
    }

    private fun saveData() {
        val editor = sharedPreferences.edit()
        val value = editTextValue.text.toString()
        val isChecked = checkBox.isChecked

        editor.putString("textValue", value)
        editor.putBoolean("isChecked", isChecked)
        editor.apply()

        Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show()
    }

    private fun loadSavedData() {
        val savedValue = sharedPreferences.getString("textValue", "")
        val isChecked = sharedPreferences.getBoolean("isChecked", false)

        editTextValue.setText(savedValue)
        checkBox.isChecked = isChecked
    }
}
