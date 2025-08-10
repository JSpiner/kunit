package io.jspiner.conversion

import io.jspiner.core.Unit

/**
 * Central conversion engine for all unit conversions in KUnit.
 * Provides efficient, inline conversion between units of the same measurement type.
 *
 * The conversion formula: targetValue = sourceValue * (sourceFactor / targetFactor)
 * where factors are relative to the base unit of the measurement type.
 */
object ConversionEngine {
    /**
     * Convert a value from one unit to another.
     *
     * @param value The numerical value in the source unit
     * @param fromUnit The source unit
     * @param toUnit The target unit
     * @return The converted value in the target unit
     * @throws IllegalArgumentException if the target unit has a zero conversion factor
     */
    fun <U : Unit> convert(
        value: Double,
        fromUnit: U,
        toUnit: U,
    ): Double {
        require(toUnit.factor != 0.0) {
            "Cannot convert to unit with zero conversion factor: ${toUnit.unitName}"
        }

        // Identity conversion optimization
        if (fromUnit === toUnit) {
            return value
        }

        // General conversion: value * (fromFactor / toFactor)
        return value * (fromUnit.factor / toUnit.factor)
    }
}
