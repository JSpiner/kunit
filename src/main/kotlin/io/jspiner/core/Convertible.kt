package io.jspiner.core

/**
 * Interface for types that can be converted between different units of measurement.
 *
 * @param U The unit type this quantity can be converted to
 * @property baseUnit The base unit for this measurement type
 */
interface Convertible<U : Unit> {
    val baseUnit: U

    /**
     * Convert this quantity to the specified unit.
     *
     * @param unit The target unit to convert to
     * @return The numerical value in the target unit
     */
    fun convertTo(unit: U): Double
}
