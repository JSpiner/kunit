package com.kunit.units.distance

/**
 * Represents a range of Distance values.
 *
 * @property start The start of the range (inclusive)
 * @property endInclusive The end of the range (inclusive)
 */
class DistanceRange(
    override val start: Distance,
    override val endInclusive: Distance,
) : ClosedRange<Distance>, Iterable<Distance> {
    /**
     * Checks whether the range is empty.
     */
    override fun isEmpty(): Boolean = start > endInclusive

    /**
     * Creates a progression from this range with the specified step.
     */
    infix fun step(step: Distance): DistanceProgression {
        return DistanceProgression.fromClosedRange(start, endInclusive, step)
    }

    override fun iterator(): Iterator<Distance> {
        return DistanceProgression.fromClosedRange(start, endInclusive, 1.m).iterator()
    }

    override fun contains(value: Distance): Boolean {
        return value >= start && value <= endInclusive
    }

    override fun equals(other: Any?): Boolean {
        return other is DistanceRange && start == other.start && endInclusive == other.endInclusive
    }

    override fun hashCode(): Int {
        return 31 * start.hashCode() + endInclusive.hashCode()
    }

    override fun toString(): String {
        return "$start..$endInclusive"
    }
}

/**
 * Creates a range from this Distance value to the specified [that] value.
 */
operator fun Distance.rangeTo(that: Distance): DistanceRange {
    return DistanceRange(this, that)
}

/**
 * Creates a half-open range that does not include its end value.
 */
infix fun Distance.until(to: Distance): DistanceRange {
    val epsilon = Distance(0.000000001) // Very small value to exclude the end
    return DistanceRange(this, to - epsilon)
}

/**
 * Ensures that this value lies in the specified range.
 */
fun Distance.coerceIn(range: DistanceRange): Distance {
    require(!range.isEmpty()) { "Cannot coerce value to an empty range: $range." }
    return when {
        this < range.start -> range.start
        this > range.endInclusive -> range.endInclusive
        else -> this
    }
}

/**
 * Ensures that this value lies between the specified minimum and maximum values.
 */
fun Distance.coerceIn(
    minimumValue: Distance,
    maximumValue: Distance,
): Distance {
    require(minimumValue <= maximumValue) {
        "Cannot coerce value to an empty range: " +
            "minimum $minimumValue is greater than maximum $maximumValue."
    }
    return when {
        this < minimumValue -> minimumValue
        this > maximumValue -> maximumValue
        else -> this
    }
}
