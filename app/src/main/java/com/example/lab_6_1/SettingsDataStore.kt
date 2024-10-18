package com.example.lab_6_1

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore private constructor(private val context: Context) {

    private val textKey = stringPreferencesKey("text_key")
    private val checkboxKey = booleanPreferencesKey("checkbox_key")

    val textFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[textKey]
        }

    val checkboxFlow: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[checkboxKey] ?: false
        }

    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[textKey] = text
        }
    }

    suspend fun saveCheckboxState(isChecked: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[checkboxKey] = isChecked
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingsDataStore? = null

        fun getInstance(context: Context): SettingsDataStore {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SettingsDataStore(context).also { INSTANCE = it }
            }
        }
    }
}
