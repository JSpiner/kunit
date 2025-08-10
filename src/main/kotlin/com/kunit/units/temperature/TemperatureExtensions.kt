package com.kunit.units.temperature

/**
 * Extension functions for creating Temperature instances from numeric values.
 */

// Temperature creation

val Number.celsius: Temperature
    get() = Temperature(this.toDouble())

val Number.fahrenheit: Temperature
    get() = Temperature((this.toDouble() - 32.0) * 5.0 / 9.0)

val Number.kelvin: Temperature
    get() = Temperature(this.toDouble() - 273.15)

val Int.celsius: Temperature
    get() = Temperature(this.toDouble())

val Int.fahrenheit: Temperature
    get() = Temperature((this.toDouble() - 32.0) * 5.0 / 9.0)

val Int.kelvin: Temperature
    get() = Temperature(this.toDouble() - 273.15)

val Double.celsius: Temperature
    get() = Temperature(this)

val Double.fahrenheit: Temperature
    get() = Temperature((this - 32.0) * 5.0 / 9.0)

val Double.kelvin: Temperature
    get() = Temperature(this - 273.15)

// Temperature difference creation

val Number.degreesCelsius: TemperatureDifference
    get() = TemperatureDifference(this.toDouble())

val Number.degreesFahrenheit: TemperatureDifference
    get() = TemperatureDifference(this.toDouble() * 5.0 / 9.0)

val Int.degreesCelsius: TemperatureDifference
    get() = TemperatureDifference(this.toDouble())

val Int.degreesFahrenheit: TemperatureDifference
    get() = TemperatureDifference(this.toDouble() * 5.0 / 9.0)

val Double.degreesCelsius: TemperatureDifference
    get() = TemperatureDifference(this)

val Double.degreesFahrenheit: TemperatureDifference
    get() = TemperatureDifference(this * 5.0 / 9.0)
