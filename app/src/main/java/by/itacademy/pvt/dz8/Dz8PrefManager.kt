package by.itacademy.pvt.dz8

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREFS_NAME = "search_field_filter"
private const val TEXT_KEY = "saved_search_field_text"

class Dz8PrefManager(context: Context) {

    private val sharedPrefs: SharedPreferences = context
        .getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveUserText(text: String) {
        sharedPrefs
            .edit()
            .putString(TEXT_KEY, text)
            .apply()
    }

    fun getUserText(): String {
        return sharedPrefs.getString(TEXT_KEY, "") ?: ""
    }
}