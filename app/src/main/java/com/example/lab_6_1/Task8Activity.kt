package com.example.lab_6_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lab_6_1.databinding.ActivityTask8Binding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class Task8Activity : AppCompatActivity() {

    private lateinit var binding: ActivityTask8Binding
    private lateinit var settingsDataStore: SettingsDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask8Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация SettingsDataStore
        settingsDataStore = SettingsDataStore.getInstance(applicationContext)

        // Слушаем изменения состояния CheckBox
        lifecycleScope.launch {
            settingsDataStore.checkboxFlow.collect { isChecked ->
                binding.checkBox.isChecked = isChecked
            }
        }

        // Слушаем изменения текста
        lifecycleScope.launch {
            settingsDataStore.textFlow.collect { text ->
                binding.editText.setText(text ?: "")
            }
        }

        // Сохраняем изменения при нажатии на кнопку
        binding.saveButton.setOnClickListener {
            val text = binding.editText.text.toString()
            val isChecked = binding.checkBox.isChecked

            // Сохраняем текст и состояние CheckBox
            lifecycleScope.launch {
                settingsDataStore.saveText(text)
                settingsDataStore.saveCheckboxState(isChecked)
            }
        }
    }
}
