package com.ericho.ultradribble.database.converter

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 *
 * Type converter for [com.ericho.ultradribble.data.User].
 * Converts [Date] to timestamp(Long) and back.
 */

class DateConverter {

    companion object {
        @JvmStatic
        @TypeConverter
        fun toDate(timestamp: Long) : Date  = Date(timestamp)

        @JvmStatic
        @TypeConverter
        fun toTimestamp(date: Date) : Long = date.time
    }

}