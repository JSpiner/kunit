package com.kunit.units.temperature

/**
 * Units of temperature measurement.
 * Note: Temperature is special because it has absolute values and conversion
 * requires offset, not just scaling.
 */
enum class TemperatureUnit(
    val symbol: String,
    val unitName: String,
) {
    CELSIUS("°C", "Celsius"),
    FAHRENHEIT("°F", "Fahrenheit"),
    KELVIN("K", "Kelvin"),
}
