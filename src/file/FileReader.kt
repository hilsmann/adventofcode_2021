package file

import java.io.File

fun readFileForInteger(fileName: String): List<Int> {
    val lineList = mutableListOf<Int>()

    File(fileName).useLines { lines -> lines.forEach { lineList.add(it.toInt()) } }

    return lineList
}

fun readFileForString(fileName: String): List<String> {
    val lineList = mutableListOf<String>()

    File(fileName).useLines { lines -> lines.forEach { lineList.add(it) } }

    return lineList
}

fun readFileForMapStringInt(fileName: String): List<Map<String, Int>> {
    val lineList = mutableListOf<Map<String, Int>>()

    File(fileName).useLines { lines
        ->
        lines.forEach {
            val splitLine = it.split(' ')
            val map = mapOf(splitLine[0] to splitLine[1].toInt())
            lineList.add(map)
        }
    }

    return lineList
}