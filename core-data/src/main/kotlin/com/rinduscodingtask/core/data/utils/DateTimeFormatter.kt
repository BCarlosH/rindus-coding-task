package com.rinduscodingtask.core.data.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

const val DAY_FORMAT = "EEE"
const val HOUR_FORMAT = "k:mm"

fun dateTimeFormatter(
    time: Int,
    format: String
): String {
    val instant = Instant.ofEpochSecond(time.toLong())
    val formatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
    return instant.atZone(ZoneId.systemDefault()).format(formatter)
}
