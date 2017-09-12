package com.ericho.ultradribble.extension

import java.io.File

/**
 * Created by Eric Ho on 2017/8/5.
 *
 * A collection of some usable functions.
 */
fun File?.dirSize(): Long {
    val dir = this
    dir?.let {
        var result = 0L
        if (dir.exists()) {
            val fileArray = dir.listFiles()
            for (f in fileArray) {
                result += if (f.isDirectory) {
                    f.dirSize()
                } else {
                    f.length()
                }
            }
            return result / 1024 / 1024
        }
        return 0
    } ?: run {
        return 0L
    }
}