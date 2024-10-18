package com.example.lab_6_1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Task6Activity : AppCompatActivity() {

    private lateinit var noteDatabaseHelper: NoteDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var editTextNote: EditText
    private val notesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task6)

        noteDatabaseHelper = NoteDatabaseHelper(this)

        editTextNote = findViewById(R.id.editTextNote)
        val buttonAddNote: Button = findViewById(R.id.buttonAddNote)
        val recyclerViewNotes: RecyclerView = findViewById(R.id.recyclerViewNotes)

        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        loadNotes()

        buttonAddNote.setOnClickListener {
            addNote()
        }
    }

    private fun loadNotes() {
        notesList.clear()
        notesList.addAll(noteDatabaseHelper.getAllNotes())
        notesAdapter = NotesAdapter(notesList) { note ->
            deleteNote(note)
        }
        findViewById<RecyclerView>(R.id.recyclerViewNotes).adapter = notesAdapter
    }

    private fun addNote() {
        val noteText = editTextNote.text.toString().trim()
        if (noteText.isNotEmpty()) {
            noteDatabaseHelper.addNote(noteText)
            editTextNote.text.clear()
            loadNotes()
            Toast.makeText(this, "Заметка добавлена", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Введите текст заметки", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteNote(note: String) {
        noteDatabaseHelper.deleteNote(note)
        loadNotes()
        Toast.makeText(this, "Заметка удалена", Toast.LENGTH_SHORT).show()
    }
}
