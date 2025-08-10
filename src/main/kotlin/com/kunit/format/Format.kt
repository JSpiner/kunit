package com.kunit.format

import com.kunit.core.Unit

/**
 * Interface for formatting quantity values with their units.
 */
interface Format {
    /**
     * Formats a value with the given unit.
     */
    fun format(
        value: Double,
        unit: Unit,
    ): String
}

/**
 * Default formatter that provides basic formatting.
 */
object DefaultFormat : Format {
    override fun format(
        value: Double,
        unit: Unit,
    ): String {
        return "$value ${unit.symbol}"
    }
}
