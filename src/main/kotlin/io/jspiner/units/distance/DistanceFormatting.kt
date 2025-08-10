package io.jspiner.units.distance

import io.jspiner.format.DecimalFormat
import io.jspiner.format.DefaultFormat
import io.jspiner.format.Format

/**
 * Formats this Distance using the default format.
 */
fun Distance.format(): String {
    return format(DefaultFormat)
}

/**
 * Formats this Distance using the specified format.
 */
fun Distance.format(format: Format): String {
    return format.format(value, baseUnit)
}

/**
 * Formats this Distance in the specified unit using the default format.
 */
fun Distance.format(unit: DistanceUnit): String {
    return format(DefaultFormat, unit)
}

/**
 * Formats this Distance in the specified unit using the specified format.
 */
fun Distance.format(
    format: Format,
    unit: DistanceUnit,
): String {
    return format.format(convertTo(unit), unit)
}

/**
 * Formats this Distance with the specified number of decimal places.
 */
fun Distance.formatWithPrecision(decimalPlaces: Int): String {
    val format = DecimalFormat(decimalPlaces = decimalPlaces)
    return format.format(value, baseUnit)
}

/**
 * Formats this Distance in the most compact unit.
 * For example, 1500m becomes "1.5 km", 0.5m becomes "50 cm"
 */
fun Distance.formatCompact(): String {
    val absValue = kotlin.math.abs(value)

    val (unit, convertedValue) =
        when {
            absValue >= 1000 -> DistanceUnit.KILOMETER to toKm()
            absValue >= 1 -> DistanceUnit.METER to toM()
            absValue >= 0.01 -> DistanceUnit.CENTIMETER to toCm()
            else -> DistanceUnit.MILLIMETER to toMm()
        }

    // Use appropriate decimal places based on the value
    val decimalPlaces =
        when {
            // Check if it's a whole number first
            kotlin.math.abs(convertedValue - kotlin.math.round(convertedValue)) < 0.0001 -> 0
            // Check if it has only one decimal place
            kotlin.math.abs(convertedValue * 10 - kotlin.math.round(convertedValue * 10)) < 0.0001 -> 1
            // Otherwise use 2 decimal places
            else -> 2
        }

    val format = DecimalFormat(decimalPlaces = decimalPlaces)
    return format.format(convertedValue, unit)
}
