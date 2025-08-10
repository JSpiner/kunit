package com.kunit.units.temperature

/**
 * Represents an absolute temperature measurement.
 * Internally stores the value in Celsius.
 *
 * This is an inline value class for zero runtime overhead.
 *
 * @property value The temperature value in Celsius
 */
@JvmInline
value class Temperature(val value: Double) : Comparable<Temperature> {
    val baseUnit: TemperatureUnit
        get() = TemperatureUnit.CELSIUS

    override fun compareTo(other: Temperature): Int = value.compareTo(other.value)

    /**
     * Calculate the difference between two temperatures.
     */
    operator fun minus(other: Temperature): TemperatureDifference {
        return TemperatureDifference(value - other.value)
    }

    /**
     * Add a temperature difference to this temperature.
     */
    operator fun plus(difference: TemperatureDifference): Temperature {
        return Temperature(value + difference.value)
    }

    /**
     * Subtract a temperature difference from this temperature.
     */
    operator fun minus(difference: TemperatureDifference): Temperature {
        return Temperature(value - difference.value)
    }

    /**
     * Convert to Celsius.
     */
    fun toCelsius(): Double = value

    /**
     * Convert to Fahrenheit.
     */
    fun toFahrenheit(): Double = value * 9.0 / 5.0 + 32.0

    /**
     * Convert to Kelvin.
     */
    fun toKelvin(): Double = value + 273.15

    override fun toString(): String = "$value °C"
}

/**
 * Represents a temperature difference (not an absolute temperature).
 * This is used for arithmetic operations on temperatures.
 *
 * @property value The temperature difference in Celsius degrees
 */
@JvmInline
value class TemperatureDifference(val value: Double) : Comparable<TemperatureDifference> {
    override fun compareTo(other: TemperatureDifference): Int = value.compareTo(other.value)

    /**
     * Add two temperature differences.
     */
    operator fun plus(other: TemperatureDifference): TemperatureDifference {
        return TemperatureDifference(value + other.value)
    }

    /**
     * Subtract another temperature difference.
     */
    operator fun minus(other: TemperatureDifference): TemperatureDifference {
        return TemperatureDifference(value - other.value)
    }

    /**
     * Multiply by a scalar.
     */
    operator fun times(factor: Double): TemperatureDifference {
        return TemperatureDifference(value * factor)
    }

    /**
     * Divide by a scalar.
     */
    operator fun div(divisor: Double): TemperatureDifference {
        return TemperatureDifference(value / divisor)
    }

    /**
     * Unary plus operator.
     */
    operator fun unaryPlus(): TemperatureDifference = this

    /**
     * Unary minus operator (negation).
     */
    operator fun unaryMinus(): TemperatureDifference = TemperatureDifference(-value)

    override fun toString(): String = "$value °C"
}
