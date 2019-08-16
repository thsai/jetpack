package com.thsai.jetpack.db

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun calenderToDate(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun DateToCalender(millis: Long): Calendar {
        return Calendar.getInstance().apply { timeInMillis = millis }
    }
}