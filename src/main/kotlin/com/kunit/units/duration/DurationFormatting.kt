package com.kunit.units.duration

import com.kunit.format.DecimalFormat
import com.kunit.format.DefaultFormat
import com.kunit.format.Format

/**
 * Formats this Duration using the default format.
 */
fun Duration.format(): String {
    return format(DefaultFormat)
}

/**
 * Formats this Duration using the specified format.
 */
fun Duration.format(format: Format): String {
    return format.format(value, baseUnit)
}

/**
 * Formats this Duration in the specified unit using the default format.
 */
fun Duration.format(unit: TimeUnit): String {
    return format(DefaultFormat, unit)
}

/**
 * Formats this Duration in the specified unit using the specified format.
 */
fun Duration.format(
    format: Format,
    unit: TimeUnit,
): String {
    return format.format(convertTo(unit), unit)
}

/**
 * Formats this Duration with the specified number of decimal places.
 */
fun Duration.formatWithPrecision(decimalPlaces: Int): String {
    val format = DecimalFormat(decimalPlaces = decimalPlaces)
    return format.format(value, baseUnit)
}

/**
 * Formats this Duration in the most compact unit(s).
 * For example, 90s becomes "1.5 min", 3661s becomes "1 h 1 min 1 s"
 */
fun Duration.formatCompact(): String {
    val totalSeconds = kotlin.math.abs(value)

    // For simple durations, use single unit
    if (totalSeconds < 60) {
        val format = DecimalFormat(decimalPlaces = if (totalSeconds < 1) 2 else 0)
        return format.format(value, TimeUnit.SECOND)
    }

    if (totalSeconds < 3600) {
        val minutes = totalSeconds / 60
        // If it's a simple fraction of minutes (like 1.5 min), show as decimal
        val remainder = totalSeconds % 60
        if (remainder == 0.0 || remainder == 30.0) {
            val decimalPlaces = if (remainder == 0.0) 0 else 1
            return DecimalFormat(decimalPlaces = decimalPlaces)
                .format(minutes, TimeUnit.MINUTE)
        }
    }

    // For complex durations, break down into components
    val hours = (totalSeconds / 3600).toInt()
    val minutes = ((totalSeconds % 3600) / 60).toInt()
    val seconds = (totalSeconds % 60).toInt()

    val parts = mutableListOf<String>()
    if (hours > 0) parts.add("$hours h")
    if (minutes > 0) parts.add("$minutes min")
    if (seconds > 0 || parts.isEmpty()) parts.add("$seconds s")

    return if (value < 0) "-${parts.joinToString(" ")}" else parts.joinToString(" ")
}
