package com.example.newsappkmp.dataSource

import android.content.Context
import android.content.SharedPreferences

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
}