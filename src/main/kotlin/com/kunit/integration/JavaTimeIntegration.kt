package com.kunit.integration

import com.kunit.units.duration.Duration
import java.time.*

/**
 * Adds a KUnit Duration to a LocalDateTime.
 */
operator fun LocalDateTime.plus(duration: Duration): LocalDateTime {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.plusSeconds(seconds).plusNanos(nanos)
}

/**
 * Subtracts a KUnit Duration from a LocalDateTime.
 */
operator fun LocalDateTime.minus(duration: Duration): LocalDateTime {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.minusSeconds(seconds).minusNanos(nanos)
}

/**
 * Adds a KUnit Duration to a LocalDate.
 * Only the days component of the duration is used.
 */
operator fun LocalDate.plus(duration: Duration): LocalDate {
    val days = (duration.toSeconds() / 86400).toLong()
    return this.plusDays(days)
}

/**
 * Subtracts a KUnit Duration from a LocalDate.
 * Only the days component of the duration is used.
 */
operator fun LocalDate.minus(duration: Duration): LocalDate {
    val days = (duration.toSeconds() / 86400).toLong()
    return this.minusDays(days)
}

/**
 * Adds a KUnit Duration to a LocalTime.
 */
operator fun LocalTime.plus(duration: Duration): LocalTime {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.plusSeconds(seconds).plusNanos(nanos)
}

/**
 * Subtracts a KUnit Duration from a LocalTime.
 */
operator fun LocalTime.minus(duration: Duration): LocalTime {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.minusSeconds(seconds).minusNanos(nanos)
}

/**
 * Adds a KUnit Duration to an Instant.
 */
operator fun Instant.plus(duration: Duration): Instant {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.plusSeconds(seconds).plusNanos(nanos)
}

/**
 * Subtracts a KUnit Duration from an Instant.
 */
operator fun Instant.minus(duration: Duration): Instant {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.minusSeconds(seconds).minusNanos(nanos)
}

/**
 * Adds a KUnit Duration to a ZonedDateTime.
 */
operator fun ZonedDateTime.plus(duration: Duration): ZonedDateTime {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.plusSeconds(seconds).plusNanos(nanos)
}

/**
 * Subtracts a KUnit Duration from a ZonedDateTime.
 */
operator fun ZonedDateTime.minus(duration: Duration): ZonedDateTime {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.minusSeconds(seconds).minusNanos(nanos)
}

/**
 * Adds a KUnit Duration to an OffsetDateTime.
 */
operator fun OffsetDateTime.plus(duration: Duration): OffsetDateTime {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.plusSeconds(seconds).plusNanos(nanos)
}

/**
 * Subtracts a KUnit Duration from an OffsetDateTime.
 */
operator fun OffsetDateTime.minus(duration: Duration): OffsetDateTime {
    // Use seconds and nanos separately to maintain precision
    val seconds = duration.toSeconds().toLong()
    val nanos = ((duration.toSeconds() - seconds) * 1_000_000_000).toLong()
    return this.minusSeconds(seconds).minusNanos(nanos)
}

/**
 * Converts a KUnit Duration to java.time.Duration.
 */
fun Duration.toJavaDuration(): java.time.Duration {
    // Use seconds and nanos separately to maintain precision
    val seconds = this.toSeconds().toLong()
    val nanos = ((this.toSeconds() - seconds) * 1_000_000_000).toLong()
    return java.time.Duration.ofSeconds(seconds, nanos)
}

/**
 * Converts a java.time.Duration to KUnit Duration.
 */
fun java.time.Duration.toKUnitDuration(): Duration {
    // Convert to seconds (Duration's internal representation)
    return Duration(this.seconds.toDouble() + this.nano / 1_000_000_000.0)
}

/**
 * Creates a java.time.Duration from a KUnit Duration.
 */
val Duration.javaDuration: java.time.Duration
    get() = toJavaDuration()

/**
 * Creates a KUnit Duration from a java.time.Duration.
 */
val java.time.Duration.kunitDuration: Duration
    get() = toKUnitDuration()