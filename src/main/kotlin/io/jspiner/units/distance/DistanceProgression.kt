package io.jspiner.units.distance

/**
 * A progression of Distance values with a fixed step.
 *
 * @property first The first element in the progression
 * @property last The last element in the progression (may differ from endInclusive if step doesn't divide evenly)
 * @property step The step between consecutive elements
 */
class DistanceProgression(
    val first: Distance,
    val last: Distance,
    val step: Distance,
) : Iterable<Distance> {
    init {
        require(step.value != 0.0) { "Step must be non-zero" }
    }

    /**
     * Checks whether the progression is empty.
     */
    fun isEmpty(): Boolean {
        return if (step.value > 0) {
            first > last
        } else {
            first < last
        }
    }

    override fun iterator(): Iterator<Distance> = DistanceProgressionIterator(first, last, step)

    /**
     * Checks if the given value is an element of this progression.
     */
    operator fun contains(element: Distance): Boolean {
        if (isEmpty()) return false

        return if (step.value > 0) {
            element >= first && element <= last && isWholeStep(element)
        } else {
            element <= first && element >= last && isWholeStep(element)
        }
    }

    private fun isWholeStep(element: Distance): Boolean {
        val diff = (element.value - first.value) / step.value
        val rounded = kotlin.math.round(diff)
        return kotlin.math.abs(diff - rounded) < 1e-10
    }

    /**
     * Returns a progression that goes over the same range with the opposite step.
     */
    fun reversed(): DistanceProgression {
        return fromClosedRange(last, first, -step)
    }

    override fun equals(other: Any?): Boolean {
        return other is DistanceProgression &&
            first == other.first &&
            last == other.last &&
            step == other.step
    }

    override fun hashCode(): Int {
        var result = first.hashCode()
        result = 31 * result + last.hashCode()
        result = 31 * result + step.hashCode()
        return result
    }

    override fun toString(): String {
        return if (step.value > 0) {
            "$first..$last step $step"
        } else {
            "$first downTo $last step ${-step}"
        }
    }

    companion object {
        /**
         * Creates a progression from a closed range with the given step.
         */
        fun fromClosedRange(
            rangeStart: Distance,
            rangeEnd: Distance,
            step: Distance,
        ): DistanceProgression {
            require(step.value != 0.0) { "Step must be non-zero" }

            val actualLast =
                if (step.value > 0) {
                    if (rangeStart >= rangeEnd) {
                        rangeEnd
                    } else {
                        val diff = rangeEnd.value - rangeStart.value
                        val stepCount = kotlin.math.floor(diff / step.value).toLong()
                        Distance(rangeStart.value + stepCount * step.value)
                    }
                } else {
                    if (rangeStart <= rangeEnd) {
                        rangeEnd
                    } else {
                        val diff = rangeEnd.value - rangeStart.value
                        val stepCount = kotlin.math.ceil(diff / step.value).toLong()
                        Distance(rangeStart.value + stepCount * step.value)
                    }
                }

            return DistanceProgression(rangeStart, actualLast, step)
        }
    }
}

/**
 * Iterator for DistanceProgression.
 */
private class DistanceProgressionIterator(
    first: Distance,
    private val lastValue: Distance,
    private val step: Distance,
) : Iterator<Distance> {
    private var hasNext: Boolean = if (step.value > 0) first <= lastValue else first >= lastValue
    private var next: Distance = if (hasNext) first else lastValue

    override fun hasNext(): Boolean = hasNext

    override fun next(): Distance {
        if (!hasNext) {
            throw NoSuchElementException()
        }
        val value = next
        if (next == lastValue) {
            hasNext = false
        } else {
            next = Distance(next.value + step.value)
            // Check if we've passed the last value
            val passedLast =
                when {
                    step.value > 0 -> next > lastValue
                    else -> next < lastValue
                }
            if (passedLast) {
                next = lastValue
            }
        }
        return value
    }
}

/**
 * Creates a progression from this value down to the specified [to] value with the given [step].
 */
infix fun Distance.downTo(to: Distance): DistanceProgression {
    return DistanceProgression.fromClosedRange(this, to, Distance(-1.0))
}

/**
 * Returns a progression that goes over the same range with the given step.
 */
infix fun DistanceProgression.step(step: Distance): DistanceProgression {
    require(step.value != 0.0) { "Step must be non-zero." }
    // When called on a downTo progression, make the step negative if positive was provided
    val actualStep =
        if (this.step.value < 0 && step.value > 0) {
            Distance(-step.value)
        } else if (this.step.value > 0 && step.value < 0) {
            Distance(-step.value)
        } else {
            step
        }
    return DistanceProgression.fromClosedRange(this.first, this.last, actualStep)
}
