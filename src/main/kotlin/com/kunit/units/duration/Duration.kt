package com.kunit.units.duration

import com.kunit.conversion.ConversionEngine
import com.kunit.core.Convertible
import com.kunit.core.Quantity

/**
 * Represents a duration/time measurement.
 * Internally stores the value in seconds (SI base unit).
 *
 * This is an inline value class for zero runtime overhead.
 *
 * @property value The duration value in seconds
 */
@JvmInline
value class Duration(override val value: Double) :
    Quantity<Duration>,
    Convertible<TimeUnit> {
    override val baseUnit: TimeUnit
        get() = TimeUnit.SECOND

    override fun compareTo(other: Duration): Int = value.compareTo(other.value)

    override fun plus(other: Duration) = Duration(value + other.value)

    override fun minus(other: Duration) = Duration(value - other.value)

    override fun times(factor: Double) = Duration(value * factor)

    override fun div(divisor: Double) = Duration(value / divisor)

    override fun unaryPlus() = this

    override fun unaryMinus() = Duration(-value)

    override fun convertTo(unit: TimeUnit): Double {
        return ConversionEngine.convert(value, TimeUnit.SECOND, unit)
    }

    /**
     * Convert to nanoseconds.
     */
    fun toNanoseconds(): Double = convertTo(TimeUnit.NANOSECOND)

    /**
     * Convert to microseconds.
     */
    fun toMicroseconds(): Double = convertTo(TimeUnit.MICROSECOND)

    /**
     * Convert to milliseconds.
     */
    fun toMilliseconds(): Double = convertTo(TimeUnit.MILLISECOND)

    /**
     * Convert to seconds.
     */
    fun toSeconds(): Double = value

    /**
     * Convert to minutes.
     */
    fun toMinutes(): Double = convertTo(TimeUnit.MINUTE)

    /**
     * Convert to hours.
     */
    fun toHours(): Double = convertTo(TimeUnit.HOUR)

    /**
     * Convert to days.
     */
    fun toDays(): Double = convertTo(TimeUnit.DAY)

    /**
     * Convert to weeks.
     */
    fun toWeeks(): Double = convertTo(TimeUnit.WEEK)

    // Shorter aliases for common conversions
    fun toNanos(): Double = toNanoseconds()
    fun toMicros(): Double = toMicroseconds()
    fun toMillis(): Double = toMilliseconds()

    override fun toString(): String = "$value s"
}
