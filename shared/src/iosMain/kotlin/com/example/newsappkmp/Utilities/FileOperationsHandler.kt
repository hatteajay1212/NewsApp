package com.example.newsappkmp.Utilities

import co.touchlab.kermit.Logger
import com.example.newsappkmp.dataSource.SharedPrefrences
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.memScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import platform.Foundation.NSData
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileHandle
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.NSUserDomainMask
import platform.Foundation.closeFile
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding
import platform.Foundation.fileHandleForWritingAtPath
import platform.Foundation.seekToEndOfFile
import platform.Foundation.stringByAppendingPathComponent
import platform.Foundation.stringWithContentsOfFile

actual class FileOperationsHandler {

    private val fileManager = NSFileManager.defaultManager
    private val documentDirectory = NSSearchPathForDirectoriesInDomains(
        directory = NSDocumentDirectory,
        domainMask = NSUserDomainMask,
        expandTilde = true
    ).first() as NSString

    private val appCode = "CMP"
    private val logFolder = "Logs"
    private val seprator : String = "/"
    val TAG = "FileOperations"

    actual suspend fun log(tag: String, data: String) {

        val logFilePath = getLogFilePath()

        val logger = Logger
        logger.i {
            TAG + "LOG FILE PATH -> $logFilePath"
        }

        val sharedPrefrences = SharedPrefrences()
        sharedPrefrences.setCurrentLogFilePath(logFilePath)

        val currentDateTime = Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
        val currentDate = currentDateTime.date
        val currentTime = currentDateTime.time

        val logData = "$appCode $currentDate $currentTime $tag $data"

        logger.i {
            logData
        }
        writeToFile(logFilePath,logData)
    }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun writeToFile(filePath: String, data: String) {

        withContext(Dispatchers.Default){
            try {
                val nsData = data.plus("\n").nsdata()
                if (nsData != null) {
                    val fileHandle = NSFileHandle.fileHandleForWritingAtPath(filePath)
                    if (fileHandle != null) {
                        fileHandle.seekToEndOfFile()
                        fileHandle.writeData(nsData,null)
                        fileHandle.closeFile()
                    }
                }
            }catch (e : Exception){
                print("Exception -> ${e.message}")
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    fun getLogFilePath() : String{
        val parentFolder = documentDirectory.stringByAppendingPathComponent(logFolder)

        if(!fileManager.fileExistsAtPath(parentFolder)){
            fileManager.createDirectoryAtPath(parentFolder,withIntermediateDirectories = true,null,null)
        }

        val currentDateTime = Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
        val currentDate = currentDateTime.date

        val currentDateFolder = parentFolder.plus(seprator).plus(currentDate)
        if(!fileManager.fileExistsAtPath(currentDateFolder)){
            fileManager.createDirectoryAtPath(currentDateFolder,withIntermediateDirectories = true, null,null)
        }

        val logFilePath = currentDateFolder.plus(seprator).plus(currentDate).plus(".txt")
        if (!fileManager.fileExistsAtPath(logFilePath)){
            fileManager.createFileAtPath(logFilePath,null,null)
        }

        return logFilePath
    }

    fun String.nsdata(): NSData? =
        NSString.create(string = this).dataUsingEncoding(NSUTF8StringEncoding)

    fun NSData.string(): String? =
        NSString.create(data = this, encoding = NSUTF8StringEncoding)?.toString()

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun readFromFile(filePath: String): String {
        return withContext(Dispatchers.Default){
            memScoped {
                val data = NSString.stringWithContentsOfFile(path = filePath, encoding = NSUTF8StringEncoding,null)
                data?: ""
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun deleteFile(filePath: String) {
        withContext(Dispatchers.Default){
            fileManager.removeItemAtPath(filePath,null)
        }
    }
}