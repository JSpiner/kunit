package com.kunit.core

/**
 * Base interface for all quantity types (Distance, Duration, Mass, etc.).
 * Provides common operations and properties for measurable quantities.
 *
 * @param T The specific quantity type (self-referential for type safety)
 * @property value The numerical value of this quantity in base units
 */
interface Quantity<T : Quantity<T>> : Comparable<T> {
    val value: Double

    /**
     * Add two quantities of the same type.
     */
    operator fun plus(other: T): T

    /**
     * Subtract another quantity from this one.
     */
    operator fun minus(other: T): T

    /**
     * Multiply this quantity by a scalar factor.
     */
    operator fun times(factor: Double): T

    /**
     * Divide this quantity by a scalar divisor.
     */
    operator fun div(divisor: Double): T

    /**
     * Unary plus operator (returns the same quantity).
     */
    operator fun unaryPlus(): T

    /**
     * Unary minus operator (negates the quantity).
     */
    operator fun unaryMinus(): T
}
