package com.kunit.format

import com.kunit.core.Unit
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

/**
 * Configuration for DecimalFormat.
 */
data class DecimalFormatConfig(
    val decimalPlaces: Int = 2,
    val useThousandsSeparator: Boolean = false,
    val showUnitSymbol: Boolean = true,
    val useFullUnitName: Boolean = false,
    val alwaysShowSign: Boolean = false,
    val useScientificNotation: Boolean = false,
    val locale: Locale = Locale.getDefault(),
)

/**
 * Formatter that provides control over decimal places and other formatting options.
 * Use DecimalFormatConfig for more options.
 */
class DecimalFormat(
    val decimalPlaces: Int = 2,
    val useThousandsSeparator: Boolean = false,
    val showUnitSymbol: Boolean = true,
    val alwaysShowSign: Boolean = false,
    val useScientificNotation: Boolean = false,
) : Format {
    val useFullUnitName: Boolean = false
    val locale: Locale = Locale.getDefault()

    private val symbols = DecimalFormatSymbols.getInstance(locale)

    override fun format(
        value: Double,
        unit: Unit,
    ): String {
        val formattedNumber =
            if (useScientificNotation) {
                formatScientific(value)
            } else {
                formatDecimal(value)
            }

        val unitString =
            when {
                !showUnitSymbol -> ""
                useFullUnitName -> " ${unit.unitName}"
                else -> " ${unit.symbol}"
            }

        return formattedNumber + unitString
    }

    private fun formatDecimal(value: Double): String {
        val sign =
            when {
                value < 0 -> "-"
                alwaysShowSign && value > 0 -> "+"
                else -> ""
            }

        val absValue = abs(value)
        val rounded = roundToDecimalPlaces(absValue, decimalPlaces)

        val integerPart = floor(rounded).toLong()
        val fractionalPart = rounded - integerPart

        val integerString =
            if (useThousandsSeparator) {
                formatWithThousandsSeparator(integerPart)
            } else {
                integerPart.toString()
            }

        return if (decimalPlaces > 0) {
            val fractionalString = formatFractionalPart(fractionalPart, decimalPlaces)
            "$sign$integerString${symbols.decimalSeparator}$fractionalString"
        } else {
            "$sign$integerString"
        }
    }

    private fun formatScientific(value: Double): String {
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
        val absValue = abs(value)

        val exponent = floor(log10(absValue)).toInt()
        val mantissa = absValue / 10.0.pow(exponent)
        val roundedMantissa = roundToDecimalPlaces(mantissa, decimalPlaces)

        val mantissaString =
            if (decimalPlaces > 0) {
                String.format(locale, "%.${decimalPlaces}f", roundedMantissa)
            } else {
                roundedMantissa.toInt().toString()
            }

        return "$sign${mantissaString}e$exponent"
    }

    private fun roundToDecimalPlaces(
        value: Double,
        places: Int,
    ): Double {
        val multiplier = 10.0.pow(places)
        return kotlin.math.round(value * multiplier) / multiplier
    }

    private fun formatWithThousandsSeparator(value: Long): String {
        val str = value.toString()
        val result = StringBuilder()
        var count = 0

        for (i in str.length - 1 downTo 0) {
            if (count == 3) {
                result.insert(0, symbols.groupingSeparator)
                count = 0
            }
            result.insert(0, str[i])
            count++
        }

        return result.toString()
    }

    private fun formatFractionalPart(
        value: Double,
        places: Int,
    ): String {
        val multiplier = 10.0.pow(places)
        val fractionalDigits = kotlin.math.round(value * multiplier).toLong()
        return fractionalDigits.toString().padStart(places, '0')
    }
}
