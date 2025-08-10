package io.jspiner.units.distance

import io.jspiner.core.Unit

/**
 * Units of distance measurement.
 * All factors are relative to meters (the base unit).
 */
enum class DistanceUnit(
    override val symbol: String,
    override val unitName: String,
    override val factor: Double,
) : Unit {
    // Metric units
    MILLIMETER("mm", "millimeter", 0.001),
    CENTIMETER("cm", "centimeter", 0.01),
    METER("m", "meter", 1.0),
    KILOMETER("km", "kilometer", 1000.0),

    // Imperial units (factors are in meters)
    INCH("in", "inch", 0.0254),
    FOOT("ft", "foot", 0.3048),
    YARD("yd", "yard", 0.9144),
    MILE("mi", "mile", 1609.344),
}
