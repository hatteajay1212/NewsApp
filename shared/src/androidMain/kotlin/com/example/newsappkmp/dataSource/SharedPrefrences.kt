package com.example.newsappkmp.dataSource

import android.content.Context
import android.content.SharedPreferences
import java.util.TreeMap

actual class SharedPrefrences(context : Context){

    var sharedPreferences : SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences("KMM_APP_PREFERENCES",
            Context.MODE_PRIVATE
        )
    }

    actual fun setMobileNumber(mobileNumber: String) {
        val editor = sharedPreferences.edit()
        editor.putString("key_mobile_number",mobileNumber)
        editor.apply()
    }

    actual fun getMobileNumber(): String {
        return sharedPreferences.getString("key_mobile_number", "") ?: ""
    }

    actual fun setEmailId(emailId: String) {
        val editor = sharedPreferences.edit()
        editor.putString("key_email_id",emailId)
        editor.apply()
    }

    actual fun getEmailId(): String {
        return sharedPreferences.getString("key_email_id", "") ?: ""
    }

    actual fun setCurrentLogFilePath(filePath: String) {
        val editor = sharedPreferences.edit()
        editor.putString("key_log_file_path",filePath)
        editor.apply()
    }

    actual fun getCurrentLogFilePath(): String {
        return sharedPreferences.getString("key_log_file_path","") ?: ""
    }
}