package io.jspiner.units.mass

import io.jspiner.conversion.ConversionEngine
import io.jspiner.core.Convertible
import io.jspiner.core.Quantity

/**
 * Represents a mass measurement.
 * Internally stores the value in grams.
 *
 * This is an inline value class for zero runtime overhead.
 *
 * @property value The mass value in grams
 */
@JvmInline
value class Mass(override val value: Double) :
    Quantity<Mass>,
    Convertible<MassUnit> {
    override val baseUnit: MassUnit
        get() = MassUnit.GRAM

    override fun compareTo(other: Mass): Int = value.compareTo(other.value)

    override fun plus(other: Mass) = Mass(value + other.value)

    override fun minus(other: Mass) = Mass(value - other.value)

    override fun times(factor: Double) = Mass(value * factor)

    override fun div(divisor: Double) = Mass(value / divisor)

    override fun unaryPlus() = this

    override fun unaryMinus() = Mass(-value)

    override fun convertTo(unit: MassUnit): Double {
        return ConversionEngine.convert(value, MassUnit.GRAM, unit)
    }

    /**
     * Convert to milligrams.
     */
    fun toMilligrams(): Double = convertTo(MassUnit.MILLIGRAM)

    /**
     * Convert to grams.
     */
    fun toGrams(): Double = value

    /**
     * Convert to kilograms.
     */
    fun toKilograms(): Double = convertTo(MassUnit.KILOGRAM)

    /**
     * Convert to metric tons.
     */
    fun toMetricTons(): Double = convertTo(MassUnit.METRIC_TON)

    /**
     * Convert to ounces.
     */
    fun toOunces(): Double = convertTo(MassUnit.OUNCE)

    /**
     * Convert to pounds.
     */
    fun toPounds(): Double = convertTo(MassUnit.POUND)

    /**
     * Convert to milligrams (short form).
     */
    fun toMg(): Double = toMilligrams()

    /**
     * Convert to grams (short form).
     */
    fun toG(): Double = toGrams()

    /**
     * Convert to kilograms (short form).
     */
    fun toKg(): Double = toKilograms()

    override fun toString(): String = "$value g"
}
