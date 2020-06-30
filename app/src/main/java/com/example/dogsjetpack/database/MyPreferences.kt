package com.example.dogsjetpack.database

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class MyPreferences {
    companion object {
        @Volatile
        private var instance: MyPreferences? = null

        private var preference: SharedPreferences? = null

        operator fun invoke(context: Context) = Companion.instance ?: synchronized(Any()) {
            instance ?: createMyPreferences(context).also {
                instance = it
            }
        }

        private fun createMyPreferences(context: Context): MyPreferences {
            preference = context.getSharedPreferences("mypreferences", 0)
            return MyPreferences()
        }
    }

    // Save system nano time
    fun saveTime(time: Long) {
        preference?.edit(commit = true) {
            putLong("time", time)
        }
    }

    // Get system nano time
    fun getTime() = preference?.getLong("time", 0L)

    fun getCacheTime() = preference?.getString("pref_cache_duration", "")
}