package com.example.newsappkmp.android

import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class Utils {

    companion object{

        fun zipFiles(inputFiles: List<File>, outputZipFile: File) {
            val bufferSize = 8192 // You can adjust the buffer size as needed

            try {
                val zipOut = ZipOutputStream(BufferedOutputStream(FileOutputStream(outputZipFile)))
                val data = ByteArray(bufferSize)

                for (inputFile in inputFiles) {
                    val fileIn = FileInputStream(inputFile)
                    val origin = BufferedInputStream(fileIn, bufferSize)

                    val entry = ZipEntry(inputFile.name)
                    zipOut.putNextEntry(entry)

                    var count: Int
                    while (origin.read(data, 0, bufferSize).also { count = it } != -1) {
                        zipOut.write(data, 0, count)
                    }

                    origin.close()
                }

                zipOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}