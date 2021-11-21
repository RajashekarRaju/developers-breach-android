package com.developerbreach.developerbreach.utils

import android.content.Context
import com.developerbreach.developerbreach.R


class PrefUtils(private val context: Context) {

    private fun prefString(value: Int): String = context.getString(value)

    private val sharedPref = context.getSharedPreferences(
        prefString(R.string.preference_intro_result_key), Context.MODE_PRIVATE
    )

    fun checkPreferenceSaveState(): Boolean {
        with(
            sharedPref.getString(
                prefString(R.string.preference_intro_status_key),
                prefString(R.string.preference_intro_fragment_not_shown_value)
            )
        ) {
            return this.equals(prefString(R.string.preference_intro_fragment_shown_value))
        }
    }

    fun preferenceStateSaved() {
        with(
            sharedPref.edit()
        ) {
            putString(
                prefString(R.string.preference_intro_status_key),
                prefString(R.string.preference_intro_fragment_shown_value)
            )
            commit()
        }
    }
}