package io.jspiner.units.mass

import io.jspiner.core.Unit

/**
 * Units of mass measurement.
 * All factors are relative to grams (the base unit).
 */
enum class MassUnit(
    override val symbol: String,
    override val unitName: String,
    override val factor: Double,
) : Unit {
    // Metric units
    MILLIGRAM("mg", "milligram", 0.001),
    GRAM("g", "gram", 1.0),
    KILOGRAM("kg", "kilogram", 1000.0),
    METRIC_TON("t", "metric ton", 1000000.0),

    // Imperial units (factors are in grams)
    OUNCE("oz", "ounce", 28.349523125),
    POUND("lb", "pound", 453.59237),
}
