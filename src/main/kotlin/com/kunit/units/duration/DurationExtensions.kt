package com.kunit.units.duration

/**
 * Extension functions for creating Duration instances from numeric values.
 * These provide a natural DSL for working with durations.
 */

val Number.nanoseconds: Duration
    get() = Duration(this.toDouble() * 1e-9)

val Number.microseconds: Duration
    get() = Duration(this.toDouble() * 1e-6)

val Number.milliseconds: Duration
    get() = Duration(this.toDouble() * 0.001)

val Number.seconds: Duration
    get() = Duration(this.toDouble())

val Number.minutes: Duration
    get() = Duration(this.toDouble() * 60.0)

val Number.hours: Duration
    get() = Duration(this.toDouble() * 3600.0)

val Number.days: Duration
    get() = Duration(this.toDouble() * 86400.0)

val Number.weeks: Duration
    get() = Duration(this.toDouble() * 604800.0)

// Int specialized extensions

val Int.nanoseconds: Duration
    get() = Duration(this * 1e-9)

val Int.microseconds: Duration
    get() = Duration(this * 1e-6)

val Int.milliseconds: Duration
    get() = Duration(this * 0.001)

val Int.seconds: Duration
    get() = Duration(this.toDouble())

val Int.minutes: Duration
    get() = Duration(this * 60.0)

val Int.hours: Duration
    get() = Duration(this * 3600.0)

val Int.days: Duration
    get() = Duration(this * 86400.0)

val Int.weeks: Duration
    get() = Duration(this * 604800.0)

// Double specialized extensions

val Double.nanoseconds: Duration
    get() = Duration(this * 1e-9)

val Double.microseconds: Duration
    get() = Duration(this * 1e-6)

val Double.milliseconds: Duration
    get() = Duration(this * 0.001)

val Double.seconds: Duration
    get() = Duration(this)

val Double.minutes: Duration
    get() = Duration(this * 60.0)

val Double.hours: Duration
    get() = Duration(this * 3600.0)

val Double.days: Duration
    get() = Duration(this * 86400.0)

val Double.weeks: Duration
    get() = Duration(this * 604800.0)
