package io.jspiner.units.mass

import io.jspiner.format.DecimalFormat
import io.jspiner.format.DefaultFormat
import io.jspiner.format.Format

/**
 * Formats this Mass using the default format.
 */
fun Mass.format(): String {
    return format(DefaultFormat)
}

/**
 * Formats this Mass using the specified format.
 */
fun Mass.format(format: Format): String {
    return format.format(value, baseUnit)
}

/**
 * Formats this Mass in the specified unit using the default format.
 */
fun Mass.format(unit: MassUnit): String {
    return format(DefaultFormat, unit)
}

/**
 * Formats this Mass in the specified unit using the specified format.
 */
fun Mass.format(
    format: Format,
    unit: MassUnit,
): String {
    return format.format(convertTo(unit), unit)
}

/**
 * Formats this Mass with the specified number of decimal places.
 */
fun Mass.formatWithPrecision(decimalPlaces: Int): String {
    val format = DecimalFormat(decimalPlaces = decimalPlaces)
    return format.format(value, baseUnit)
}

/**
 * Formats this Mass in the most compact unit.
 * For example, 1500g becomes "1.5 kg", 0.5g becomes "500 mg"
 */
fun Mass.formatCompact(): String {
    val absValue = kotlin.math.abs(value)

    val (unit, convertedValue) =
        when {
            absValue >= 1000 -> MassUnit.KILOGRAM to toKg()
            absValue >= 1 -> MassUnit.GRAM to toG()
            else -> MassUnit.MILLIGRAM to toMg()
        }

    // Use appropriate decimal places based on the value
    val decimalPlaces =
        when {
            kotlin.math.abs(convertedValue) >= 100 -> 0
            kotlin.math.abs(convertedValue) >= 10 -> 1
            else -> 2
        }

    val format = DecimalFormat(decimalPlaces = decimalPlaces)
    return format.format(convertedValue, unit)
}
