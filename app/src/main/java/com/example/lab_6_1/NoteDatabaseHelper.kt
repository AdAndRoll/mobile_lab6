package com.example.lab_6_1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class NoteDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notes.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NOTES = "notes"
        const val COLUMN_ID = "_id"
        const val COLUMN_NOTE = "note"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NOTES ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_NOTE TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }

    // Функция для добавления заметки
    fun addNote(note: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOTE, note)
        }
        db.insert(TABLE_NOTES, null, values)
        db.close()
    }

    // Функция для получения всех заметок
    fun getAllNotes(): List<String> {
        val notes = mutableListOf<String>()
        val db = readableDatabase
        val cursor: Cursor = db.query(TABLE_NOTES, arrayOf(COLUMN_NOTE), null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                notes.add(cursor.getString(0))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return notes
    }

    // Функция для удаления заметки
    fun deleteNote(note: String) {
        val db = writableDatabase
        db.delete(TABLE_NOTES, "$COLUMN_NOTE = ?", arrayOf(note))
        db.close()
    }
}
