package com.kunit.format

import com.kunit.core.Unit
import java.text.DecimalFormat as JavaDecimalFormat

/**
 * Formatter that uses a custom pattern string for formatting.
 * Pattern follows java.text.DecimalFormat pattern syntax.
 */
class PatternFormat(
    val pattern: String,
    val showUnitSymbol: Boolean = true,
) : Format {
    private val formatter = JavaDecimalFormat(pattern)

    override fun format(
        value: Double,
        unit: Unit,
    ): String {
        val formattedNumber = formatter.format(value)
        return if (showUnitSymbol) {
            "$formattedNumber ${unit.symbol}"
        } else {
            formattedNumber
        }
    }
}
