package com.kunit.units.distance

/**
 * Extension functions for creating Distance instances from numeric values.
 * These provide a natural DSL for working with distances.
 */

/**
 * Creates a Distance from a Number representing kilometers.
 */
val Number.km: Distance
    get() = Distance(this.toDouble() * 1000.0)

/**
 * Creates a Distance from a Number representing meters.
 */
val Number.m: Distance
    get() = Distance(this.toDouble())

/**
 * Creates a Distance from a Number representing centimeters.
 */
val Number.cm: Distance
    get() = Distance(this.toDouble() * 0.01)

/**
 * Creates a Distance from a Number representing millimeters.
 */
val Number.mm: Distance
    get() = Distance(this.toDouble() * 0.001)

/**
 * Creates a Distance from an Int representing kilometers.
 * This specialized version avoids unnecessary conversions.
 */
val Int.km: Distance
    get() = Distance(this * 1000.0)

/**
 * Creates a Distance from an Int representing meters.
 * This specialized version avoids unnecessary conversions.
 */
val Int.m: Distance
    get() = Distance(this.toDouble())

/**
 * Creates a Distance from an Int representing centimeters.
 * This specialized version avoids unnecessary conversions.
 */
val Int.cm: Distance
    get() = Distance(this * 0.01)

/**
 * Creates a Distance from an Int representing millimeters.
 * This specialized version avoids unnecessary conversions.
 */
val Int.mm: Distance
    get() = Distance(this * 0.001)

/**
 * Creates a Distance from a Double representing kilometers.
 * This specialized version avoids unnecessary conversions.
 */
val Double.km: Distance
    get() = Distance(this * 1000.0)

/**
 * Creates a Distance from a Double representing meters.
 * This specialized version avoids unnecessary conversions.
 */
val Double.m: Distance
    get() = Distance(this)

/**
 * Creates a Distance from a Double representing centimeters.
 * This specialized version avoids unnecessary conversions.
 */
val Double.cm: Distance
    get() = Distance(this * 0.01)

/**
 * Creates a Distance from a Double representing millimeters.
 * This specialized version avoids unnecessary conversions.
 */
val Double.mm: Distance
    get() = Distance(this * 0.001)

// Imperial units

/**
 * Creates a Distance from a Number representing inches.
 */
val Number.inches: Distance
    get() = Distance(this.toDouble() * 0.0254)

/**
 * Creates a Distance from a Number representing feet.
 */
val Number.feet: Distance
    get() = Distance(this.toDouble() * 0.3048)

/**
 * Creates a Distance from a Number representing yards.
 */
val Number.yards: Distance
    get() = Distance(this.toDouble() * 0.9144)

/**
 * Creates a Distance from a Number representing miles.
 */
val Number.miles: Distance
    get() = Distance(this.toDouble() * 1609.344)

/**
 * Creates a Distance from an Int representing inches.
 */
val Int.inches: Distance
    get() = Distance(this * 0.0254)

/**
 * Creates a Distance from an Int representing feet.
 */
val Int.feet: Distance
    get() = Distance(this * 0.3048)

/**
 * Creates a Distance from an Int representing yards.
 */
val Int.yards: Distance
    get() = Distance(this * 0.9144)

/**
 * Creates a Distance from an Int representing miles.
 */
val Int.miles: Distance
    get() = Distance(this * 1609.344)

/**
 * Creates a Distance from a Double representing inches.
 */
val Double.inches: Distance
    get() = Distance(this * 0.0254)

/**
 * Creates a Distance from a Double representing feet.
 */
val Double.feet: Distance
    get() = Distance(this * 0.3048)

/**
 * Creates a Distance from a Double representing yards.
 */
val Double.yards: Distance
    get() = Distance(this * 0.9144)

/**
 * Creates a Distance from a Double representing miles.
 */
val Double.miles: Distance
    get() = Distance(this * 1609.344)
