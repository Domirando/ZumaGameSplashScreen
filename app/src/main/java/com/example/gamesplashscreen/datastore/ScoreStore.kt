package com.example.gamesplashscreen.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

class ScoreStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("max_score")
        val MAX_SCORE = intPreferencesKey("max_score")
    }

    val getScore = context.dataStore.data
        .map { preferences ->
            preferences[MAX_SCORE] ?: 0
        }
    suspend fun saveScore(score: Int){
        context.dataStore.edit { preferences ->
            preferences[MAX_SCORE] = score
        }
    }
}