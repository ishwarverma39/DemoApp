package com.livtech.demo.core

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * Created by jiffler on 15/09/15.
 */
object PreferenceManager {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var mEditor: SharedPreferences.Editor
    private const val PREFERENCE_NAME = "preference"

    @SuppressLint("CommitPrefEdits")
    fun initPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(
            PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
        mEditor = sharedPreferences.edit()
    }

    fun clearPreferences() {
        mEditor.clear().commit()
    }

    fun saveStringValue(key: String?, value: String?) {
        mEditor.putString(key, value).commit()
    }

    fun getStringValue(key: String?): String? {
        return sharedPreferences.getString(key, "")
    }

    fun saveLongValue(key: String?, value: Long) {
        mEditor.putLong(key, value).commit()
    }

    fun saveIntValue(key: String?, value: Int) {
        mEditor.putInt(key, value).commit()
    }

    fun getLongValue(key: String?): Long {
        return sharedPreferences.getLong(key, -1)
    }

    fun getIntValue(key: String?): Int {
        return sharedPreferences.getInt(key, -1)
    }

    fun saveBoolValue(key: String?, value: Boolean) {
        mEditor.putBoolean(key, value).commit()
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defValue)
    }
}