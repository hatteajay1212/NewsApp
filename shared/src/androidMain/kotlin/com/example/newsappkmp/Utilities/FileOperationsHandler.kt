package com.example.newsappkmp.Utilities

import android.content.Context
import android.os.Build
import android.os.Environment
import co.touchlab.kermit.Logger
import com.example.newsappkmp.dataSource.SharedPrefrences
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.io.File
import java.io.FileWriter
import java.lang.Exception

actual class FileOperationsHandler(
    private val context: Context
) {
    val parentFolder = "CMP"
    val logFolder = "Logs"
    val maxLogFileSize = 1024 * 1024 * 10
    val TAG = "FileOperations"

    actual suspend fun log(tag: String, data: String) {
        val logFilePath = getLogFilePath()

        val logger = Logger
        logger.i {
            TAG + "LOG FILE PATH -> $logFilePath"
        }

        val sharedPrefrences = SharedPrefrences(context)
        sharedPrefrences.setCurrentLogFilePath(logFilePath)

        val currentDateTime = Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
        val currentDate = currentDateTime.date
        val currentTime = currentDateTime.time
        val logData = "$parentFolder $currentDate $currentTime $tag $data"
        writeToFile(logFilePath,logData)
    }

    actual suspend fun writeToFile(filePath: String, data: String) {
        try {
            val logFile = File(filePath)
            val writer = FileWriter(logFile, true) // true for append mode
            writer.append("$data\n")
            writer.close()
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    actual suspend fun readFromFile(filePath: String): String {
        TODO("Not yet implemented")
    }

    actual suspend fun deleteFile(filePath: String) {
    }

    private fun getRootPath() : String{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            return context.filesDir.absolutePath
        }else{
            return Environment.getExternalStorageDirectory().absolutePath
        }
    }

    private fun getLogFilePath() : String{

        val rootPath = getRootPath()

        val parentFolder = File(rootPath + File.separator + parentFolder)

        if (!parentFolder.exists()){
            parentFolder.mkdir()
        }

        val logFolder = File(parentFolder.absolutePath + File.separator + logFolder)
        if (!logFolder.exists()){
            logFolder.mkdir()
        }

        val logDateFolderName = Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault()).date

        val logDateFolder = File(logFolder.absolutePath + File.separator + logDateFolderName)

        if (!logDateFolder.exists()){
            logDateFolder.mkdir()
        }

        val logFilePath = logDateFolder.absolutePath + File.separator + logDateFolderName + ".txt"
        val logFile = File(logFilePath)
        if (logFile.exists()){
            if (maxLogFileSize <= logFile.length()){
                logFilePath
            }
        }else{
            logFile.createNewFile()
        }

        return logFilePath
    }
}