package com.deloitte.deloittetask.repository.local_data_source.shared_preference

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val editor: SharedPreferences.Editor,
     val gson: Gson
) {

    fun saveString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }


    fun saveInteger(key: String, value: Int) {
        editor.putInt(key, value)
        editor.apply()
    }

    fun saveLong(key: String, value: Long) {
        editor.putLong(key, value)
        editor.apply()
    }
    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String): String? =
        sharedPreferences.getString(key, defaultValue)
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)

    fun getInt(key: String): Int = sharedPreferences.getInt(key, -1)
    fun getLong(key: String): Long = sharedPreferences.getLong(key,-1)
}
