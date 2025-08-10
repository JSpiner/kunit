package io.jspiner.units.distance

/**
 * Returns the sum of all distances in the collection.
 * Returns 0.m for an empty collection.
 */
fun Iterable<Distance>.sum(): Distance {
    var sum = 0.0
    for (element in this) {
        sum += element.value
    }
    return Distance(sum)
}

/**
 * Returns the sum of all distances in the array.
 * Returns 0.m for an empty array.
 */
fun Array<out Distance>.sum(): Distance {
    var sum = 0.0
    for (element in this) {
        sum += element.value
    }
    return Distance(sum)
}

/**
 * Returns the average of all distances in the collection.
 * @throws IllegalArgumentException if the collection is empty
 */
fun Iterable<Distance>.average(): Distance {
    var sum = 0.0
    var count = 0
    for (element in this) {
        sum += element.value
        count++
    }
    require(count != 0) { "Cannot compute average of empty collection" }
    return Distance(sum / count)
}

/**
 * Returns the average of all distances in the array.
 * @throws IllegalArgumentException if the array is empty
 */
fun Array<out Distance>.average(): Distance {
    require(isNotEmpty()) { "Cannot compute average of empty array" }
    var sum = 0.0
    for (element in this) {
        sum += element.value
    }
    return Distance(sum / size)
}

/**
 * Returns the sum of all values produced by selector function applied to each element in the collection.
 */
inline fun <T> Iterable<T>.sumOf(selector: (T) -> Distance): Distance {
    var sum = 0.0
    for (element in this) {
        sum += selector(element).value
    }
    return Distance(sum)
}

/**
 * Returns the sum of all values produced by selector function applied to each element in the array.
 */
inline fun <T> Array<out T>.sumOf(selector: (T) -> Distance): Distance {
    var sum = 0.0
    for (element in this) {
        sum += selector(element).value
    }
    return Distance(sum)
}
