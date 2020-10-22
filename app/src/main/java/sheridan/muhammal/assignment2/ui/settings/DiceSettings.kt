package sheridan.muhammal.assignment2.ui.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import sheridan.muhammal.assignment2.R

class DiceSettings(private val context: Context){

    private val preferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    val confirmDelete: Boolean
        get() = preferences.getBoolean("confirm_delete", true)

    val confirmClear: Boolean
        get() = preferences.getBoolean("confirm_clear", true)
}