package com.kunit.core

/**
 * Base interface for all measurement units in KUnit.
 *
 * @property symbol The abbreviated symbol for this unit (e.g., "m", "kg", "s")
 * @property unitName The full name of this unit (e.g., "meter", "kilogram", "second")
 * @property factor The conversion factor to the base unit of this measurement type
 */
interface Unit {
    val symbol: String
    val unitName: String
    val factor: Double
}
