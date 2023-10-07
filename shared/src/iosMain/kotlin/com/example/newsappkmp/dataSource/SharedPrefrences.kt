package com.example.newsappkmp.dataSource
import platform.Foundation.NSUserDefaults

actual class SharedPrefrences{

    actual fun setMobileNumber(mobileNumber: String) {
        NSUserDefaults.standardUserDefaults.setObject(mobileNumber, "key_mobile_number")
    }

    actual fun getMobileNumber(): String {
        return NSUserDefaults.standardUserDefaults.stringForKey("key_mobile_number") ?: ""
    }

    actual fun setEmailId(emailId: String) {
        NSUserDefaults.standardUserDefaults.setObject(emailId, "key_email_id")
    }

    actual fun getEmailId(): String {
        return NSUserDefaults.standardUserDefaults.stringForKey("key_email_id") ?: ""
    }
}