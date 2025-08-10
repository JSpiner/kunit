package io.jspiner.units.mass

/**
 * Extension functions for creating Mass instances from numeric values.
 * These provide a natural DSL for working with mass measurements.
 */

val Number.mg: Mass
    get() = Mass(this.toDouble() * 0.001)

val Number.g: Mass
    get() = Mass(this.toDouble())

val Number.kg: Mass
    get() = Mass(this.toDouble() * 1000.0)

val Number.tons: Mass
    get() = Mass(this.toDouble() * 1000000.0)

// Imperial units

val Number.oz: Mass
    get() = Mass(this.toDouble() * 28.349523125)

val Number.lb: Mass
    get() = Mass(this.toDouble() * 453.59237)

// Int specialized extensions

val Int.mg: Mass
    get() = Mass(this * 0.001)

val Int.g: Mass
    get() = Mass(this.toDouble())

val Int.kg: Mass
    get() = Mass(this * 1000.0)

val Int.tons: Mass
    get() = Mass(this * 1000000.0)

val Int.oz: Mass
    get() = Mass(this * 28.349523125)

val Int.lb: Mass
    get() = Mass(this * 453.59237)

// Double specialized extensions

val Double.mg: Mass
    get() = Mass(this * 0.001)

val Double.g: Mass
    get() = Mass(this)

val Double.kg: Mass
    get() = Mass(this * 1000.0)

val Double.tons: Mass
    get() = Mass(this * 1000000.0)

val Double.oz: Mass
    get() = Mass(this * 28.349523125)

val Double.lb: Mass
    get() = Mass(this * 453.59237)
