package com.kunit.format

import com.kunit.core.Unit
import java.text.NumberFormat
import java.util.Locale

/**
 * Formatter that uses locale-specific number formatting.
 */
class LocaleFormat(
    val locale: Locale,
    val useFullUnitName: Boolean = false,
    val maxFractionDigits: Int = 2,
    val minFractionDigits: Int = 0,
) : Format {
    private val numberFormat =
        NumberFormat.getNumberInstance(locale).apply {
            maximumFractionDigits = maxFractionDigits
            minimumFractionDigits = minFractionDigits
        }

    override fun format(
        value: Double,
        unit: Unit,
    ): String {
        val formattedNumber = numberFormat.format(value)
        val unitString = getLocalizedUnitString(unit)
        return "$formattedNumber $unitString"
    }

    private fun getLocalizedUnitString(unit: Unit): String {
        return if (useFullUnitName) {
            getLocalizedUnitName(unit)
        } else {
            unit.symbol
        }
    }

    private fun getLocalizedUnitName(unit: Unit): String {
        // In a real implementation, this would use resource bundles for localization
        // For now, we'll provide basic English translations with plural
        return if (locale.language == "de") {
            getGermanUnitName(unit)
        } else {
            getEnglishUnitName(unit)
        }
    }

    private fun getGermanUnitName(unit: Unit): String {
        val germanNames =
            mapOf(
                "meter" to "Meter",
                "kilometer" to "Kilometer",
                "centimeter" to "Zentimeter",
                "millimeter" to "Millimeter",
                "second" to "Sekunde",
                "minute" to "Minute",
                "hour" to "Stunde",
                "kilogram" to "Kilogramm",
                "gram" to "Gramm",
            )
        return germanNames[unit.unitName] ?: unit.unitName
    }

    private fun getEnglishUnitName(unit: Unit): String {
        val englishNames =
            mapOf(
                "meter" to "meters",
                "kilometer" to "kilometers",
                "centimeter" to "centimeters",
                "millimeter" to "millimeters",
                "second" to "seconds",
                "minute" to "minutes",
                "hour" to "hours",
                "kilogram" to "kilograms",
                "gram" to "grams",
            )
        return englishNames[unit.unitName] ?: (unit.unitName + "s")
    }
}
