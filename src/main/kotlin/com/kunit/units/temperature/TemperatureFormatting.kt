package com.kunit.units.temperature

import com.kunit.format.DecimalFormat
import com.kunit.format.LocaleFormat
import kotlin.math.pow

/**
 * Formats this Temperature using the default format in Celsius.
 */
fun Temperature.format(): String {
    return "$value ${baseUnit.symbol}"
}

/**
 * Formats this Temperature using the specified formatter in Celsius.
 */
fun Temperature.format(formatter: DecimalFormat): String {
    return formatter.formatTemperature(value, baseUnit)
}

/**
 * Formats this Temperature using the specified formatter in Celsius.
 */
fun Temperature.format(formatter: LocaleFormat): String {
    return formatter.formatTemperature(value, baseUnit)
}

/**
 * Formats this Temperature in the specified unit using the default format.
 */
fun Temperature.format(unit: TemperatureUnit): String {
    val convertedValue =
        when (unit) {
            TemperatureUnit.CELSIUS -> toCelsius()
            TemperatureUnit.FAHRENHEIT -> toFahrenheit()
            TemperatureUnit.KELVIN -> toKelvin()
        }
    return "$convertedValue ${unit.symbol}"
}

/**
 * Formats this Temperature in the specified unit using the specified formatter.
 */
fun Temperature.format(
    formatter: DecimalFormat,
    unit: TemperatureUnit,
): String {
    val convertedValue =
        when (unit) {
            TemperatureUnit.CELSIUS -> toCelsius()
            TemperatureUnit.FAHRENHEIT -> toFahrenheit()
            TemperatureUnit.KELVIN -> toKelvin()
        }
    return formatter.formatTemperature(convertedValue, unit)
}

/**
 * Formats this Temperature with the specified number of decimal places.
 */
fun Temperature.formatWithPrecision(decimalPlaces: Int): String {
    val formatter = DecimalFormat(decimalPlaces = decimalPlaces)
    return formatter.formatTemperature(value, baseUnit)
}

/**
 * Formats this Temperature in the most appropriate unit based on locale or value.
 */
fun Temperature.formatCompact(): String {
    val formatter = DecimalFormat(decimalPlaces = 1)
    return formatter.formatTemperature(value, baseUnit)
}

// Extension functions for formatters to handle temperature
fun DecimalFormat.formatTemperature(
    value: Double,
    unit: TemperatureUnit,
): String {
    val formattedNumber =
        if (useScientificNotation) {
            formatScientificNumber(value)
        } else {
            formatDecimalNumber(value)
        }

    val unitString =
        when {
            !showUnitSymbol -> ""
            useFullUnitName -> " ${unit.unitName}"
            else -> " ${unit.symbol}"
        }

    return formattedNumber + unitString
}

private fun DecimalFormat.formatDecimalNumber(value: Double): String {
    val sign =
        when {
            value < 0 -> "-"
            alwaysShowSign && value > 0 -> "+"
            else -> ""
        }

    val absValue = kotlin.math.abs(value)
    val rounded = kotlin.math.round(absValue * 10.0.pow(decimalPlaces)) / 10.0.pow(decimalPlaces)

    val integerPart = kotlin.math.floor(rounded).toLong()
    val fractionalPart = rounded - integerPart

    val integerString =
        if (useThousandsSeparator) {
            formatWithThousandsSeparatorHelper(integerPart)
        } else {
            integerPart.toString()
        }

    return if (decimalPlaces > 0) {
        val fractionalDigits = kotlin.math.round(fractionalPart * 10.0.pow(decimalPlaces)).toLong()
        val fractionalString = fractionalDigits.toString().padStart(decimalPlaces, '0')
        "$sign$integerString.$fractionalString"
    } else {
        "$sign$integerString"
    }
}

private fun DecimalFormat.formatScientificNumber(value: Double): String {
    if (value == 0.0) {
        return if (decimalPlaces > 0) {
            "0.${"0".repeat(decimalPlaces)}e0"
        } else {
            "0e0"
        }
    }

    val sign =
        if (value < 0) {
            "-"
        } else if (alwaysShowSign) {
            "+"
        } else {
            ""
        }
    val absValue = kotlin.math.abs(value)

    val exponent = kotlin.math.floor(kotlin.math.log10(absValue)).toInt()
    val mantissa = absValue / 10.0.pow(exponent)
    val roundedMantissa = kotlin.math.round(mantissa * 10.0.pow(decimalPlaces)) / 10.0.pow(decimalPlaces)

    val mantissaString =
        if (decimalPlaces > 0) {
            String.format(locale, "%.${decimalPlaces}f", roundedMantissa)
        } else {
            roundedMantissa.toInt().toString()
        }

    return "$sign${mantissaString}e$exponent"
}

private fun formatWithThousandsSeparatorHelper(value: Long): String {
    val str = value.toString()
    val result = StringBuilder()
    var count = 0

    for (i in str.length - 1 downTo 0) {
        if (count == 3) {
            result.insert(0, ',')
            count = 0
        }
        result.insert(0, str[i])
        count++
    }

    return result.toString()
}

fun LocaleFormat.formatTemperature(
    value: Double,
    unit: TemperatureUnit,
): String {
    val numberFormat =
        java.text.NumberFormat.getNumberInstance(locale).apply {
            maximumFractionDigits = maxFractionDigits
            minimumFractionDigits = minFractionDigits
        }
    val formattedNumber = numberFormat.format(value)
    val unitString =
        if (useFullUnitName) {
            getLocalizedTemperatureUnitName(unit)
        } else {
            unit.symbol
        }
    return "$formattedNumber $unitString"
}

private fun LocaleFormat.getLocalizedTemperatureUnitName(unit: TemperatureUnit): String {
    return when (locale.language) {
        "de" ->
            when (unit) {
                TemperatureUnit.CELSIUS -> "Grad Celsius"
                TemperatureUnit.FAHRENHEIT -> "Grad Fahrenheit"
                TemperatureUnit.KELVIN -> "Kelvin"
            }
        else -> unit.unitName
    }
}
