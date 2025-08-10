package io.jspiner.integration

import io.jspiner.units.duration.Duration
import kotlin.time.toDuration
import kotlin.time.Duration as KotlinDuration
import kotlin.time.DurationUnit as KotlinDurationUnit

/**
 * Convert KUnit Duration to kotlin.time.Duration
 */
fun Duration.toKotlinDuration(): KotlinDuration {
    return value.toDuration(KotlinDurationUnit.SECONDS)
}

/**
 * Convert kotlin.time.Duration to KUnit Duration
 */
fun KotlinDuration.toKUnitDuration(): Duration {
    return Duration(this.inWholeNanoseconds / 1_000_000_000.0)
}
