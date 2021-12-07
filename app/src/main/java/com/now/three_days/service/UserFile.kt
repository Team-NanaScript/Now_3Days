package com.now.three_days.service

import android.util.Log
import java.io.*
import java.lang.StringBuilder

class UserFile(path:String) {

    private var dirPath:String = ""

    init {
        dirPath= path
    }

    fun userLog():Boolean {
        var checkUser = true

        val fileLog = readFile("memo/test")

        if(fileLog == null || fileLog == "") {
            checkUser = false
            Log.d("user file log", "No log")
        }

        return checkUser
    }

    private fun readFile(path: String): String {
        val fullPath = File(dirPath + "/" + path)
        if (!fullPath.exists()) return ""

        val reader = FileReader(fullPath)
        val buffer = BufferedReader(reader)

        val result = StringBuilder()
        var temp: String? = ""

        while (true) {
            temp = buffer.readLine()
            if (temp == null) break
            result.append(temp).append("\n")
        }
        buffer.close()
        reader.close()
        return result.toString()
    }

    fun writeFile(directory: String, fileName: String, content: String) {
        val dir = File(dirPath + "/" + directory)
        if (!dir.exists()) dir.mkdirs()

        val fullPath = dir.path + "/" + fileName
        val writer = FileWriter(fullPath)
        val buffer = BufferedWriter(writer)
        buffer.write(content)
        buffer.close()
        writer.close()
    }

    fun pathReturn():String {
        return dirPath
    }

    fun remove(path:String) {
        var file = File(dirPath+"/"+path)
        Log.d("file path",file.path)
//        file.delete()
    }

}