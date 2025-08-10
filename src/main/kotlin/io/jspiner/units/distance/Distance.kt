package io.jspiner.units.distance

import io.jspiner.conversion.ConversionEngine
import io.jspiner.core.Convertible
import io.jspiner.core.Quantity

/**
 * Represents a distance measurement.
 * Internally stores the value in meters (SI base unit).
 *
 * This is an inline value class for zero runtime overhead.
 *
 * @property value The distance value in meters
 */
@JvmInline
value class Distance(override val value: Double) :
    Quantity<Distance>,
    Convertible<DistanceUnit> {
    override val baseUnit: DistanceUnit
        get() = DistanceUnit.METER

    override fun compareTo(other: Distance): Int = value.compareTo(other.value)

    override fun plus(other: Distance) = Distance(value + other.value)

    override fun minus(other: Distance) = Distance(value - other.value)

    override fun times(factor: Double) = Distance(value * factor)

    override fun div(divisor: Double) = Distance(value / divisor)

    override fun unaryPlus() = this

    override fun unaryMinus() = Distance(-value)

    override fun convertTo(unit: DistanceUnit): Double {
        return ConversionEngine.convert(value, DistanceUnit.METER, unit)
    }

    /**
     * Convert to meters.
     */
    fun toM(): Double = value

    /**
     * Convert to kilometers.
     */
    fun toKm(): Double = convertTo(DistanceUnit.KILOMETER)

    /**
     * Convert to centimeters.
     */
    fun toCm(): Double = convertTo(DistanceUnit.CENTIMETER)

    /**
     * Convert to millimeters.
     */
    fun toMm(): Double = convertTo(DistanceUnit.MILLIMETER)

    /**
     * Convert to inches.
     */
    fun toInches(): Double = convertTo(DistanceUnit.INCH)

    /**
     * Convert to feet.
     */
    fun toFeet(): Double = convertTo(DistanceUnit.FOOT)

    /**
     * Convert to yards.
     */
    fun toYards(): Double = convertTo(DistanceUnit.YARD)

    /**
     * Convert to miles.
     */
    fun toMiles(): Double = convertTo(DistanceUnit.MILE)

    override fun toString(): String = "$value m"
}
