package com.example.newsappkmp.dataSource

expect class SharedPrefrences{
    fun setMobileNumber(mobileNumber : String)
    fun getMobileNumber() : String
    fun setEmailId(emailId : String)
    fun getEmailId() : String
}