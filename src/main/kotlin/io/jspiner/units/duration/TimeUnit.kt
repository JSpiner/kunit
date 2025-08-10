package io.jspiner.units.duration

import io.jspiner.core.Unit

/**
 * Units of time/duration measurement.
 * All factors are relative to seconds (the base unit).
 */
enum class TimeUnit(
    override val symbol: String,
    override val unitName: String,
    override val factor: Double,
) : Unit {
    NANOSECOND("ns", "nanosecond", 1e-9),
    MICROSECOND("μs", "microsecond", 1e-6),
    MILLISECOND("ms", "millisecond", 0.001),
    SECOND("s", "second", 1.0),
    MINUTE("min", "minute", 60.0),
    HOUR("h", "hour", 3600.0),
    DAY("d", "day", 86400.0),
    WEEK("w", "week", 604800.0),
}
