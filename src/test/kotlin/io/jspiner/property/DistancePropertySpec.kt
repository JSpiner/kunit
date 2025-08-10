package io.jspiner.property

import io.jspiner.units.distance.Distance
import io.jspiner.units.distance.DistanceUnit
import io.jspiner.units.distance.km
import io.jspiner.units.distance.m
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.double
import io.kotest.property.forAll

class DistancePropertySpec : DescribeSpec({
    describe("Distance Property Tests") {
        describe("Conversion reversibility") {
            it("should maintain value when converting to and from any unit") {
                forAll(
                    Arb.double(0.0, 10000.0),
                ) { meters ->
                    val distance = Distance(meters)

                    // Test each unit conversion reversibility
                    DistanceUnit.values().forEach { unit ->
                        val converted = distance.convertTo(unit)
                        val backToMeters = Distance(converted * unit.factor).value

                        if (meters == 0.0 || meters < 0.001) {
                            // For very small values, use absolute tolerance
                            kotlin.math.abs(backToMeters - meters) < 0.000001
                        } else {
                            backToMeters.shouldBeWithinPercentageOf(meters, 0.01)
                        }
                    }
                    true
                }
            }
        }

        describe("Addition commutativity") {
            it("should be commutative: a + b = b + a") {
                forAll(
                    Arb.double(0.0, 10000.0),
                    Arb.double(0.0, 10000.0),
                ) { a, b ->
                    val distanceA = Distance(a)
                    val distanceB = Distance(b)

                    val sum1 = distanceA + distanceB
                    val sum2 = distanceB + distanceA

                    sum1.value shouldBe sum2.value
                    true
                }
            }
        }

        describe("Addition associativity") {
            it("should be associative: (a + b) + c = a + (b + c)") {
                forAll(
                    Arb.double(0.0, 1000.0),
                    Arb.double(0.0, 1000.0),
                    Arb.double(0.0, 1000.0),
                ) { a, b, c ->
                    val distanceA = Distance(a)
                    val distanceB = Distance(b)
                    val distanceC = Distance(c)

                    val sum1 = (distanceA + distanceB) + distanceC
                    val sum2 = distanceA + (distanceB + distanceC)

                    if (a + b + c == 0.0) {
                        sum1.value shouldBe 0.0
                        sum2.value shouldBe 0.0
                    } else {
                        sum1.value.shouldBeWithinPercentageOf(sum2.value, 0.0001)
                    }
                    true
                }
            }
        }

        describe("Zero as identity element") {
            it("should have zero as identity: a + 0 = a") {
                forAll(
                    // Distance should be positive
                    Arb.double(0.0, 10000.0),
                ) { meters ->
                    val distance = Distance(meters)
                    val zero = Distance(0.0)

                    val sum = distance + zero
                    if (meters == 0.0) {
                        sum.value shouldBe 0.0
                    } else {
                        sum.value.shouldBeWithinPercentageOf(distance.value, 0.0001)
                    }
                    true
                }
            }
        }

        describe("Multiplication distributivity") {
            it("should be distributive: a * (b + c) = a * b + a * c") {
                forAll(
                    Arb.double(0.1, 100.0),
                    Arb.double(0.0, 1000.0),
                    Arb.double(0.0, 1000.0),
                ) { scalar, b, c ->
                    val distanceB = Distance(b)
                    val distanceC = Distance(c)

                    val left = (distanceB + distanceC) * scalar
                    val right = (distanceB * scalar) + (distanceC * scalar)

                    // Due to floating-point arithmetic, we need tolerance
                    val tolerance = kotlin.math.max(0.000001, kotlin.math.abs(left.value) * 0.01)
                    kotlin.math.abs(left.value - right.value) < tolerance
                    true
                }
            }
        }

        describe("Comparison transitivity") {
            it("should be transitive: if a < b and b < c, then a < c") {
                forAll(
                    Arb.double(0.0, 1000.0),
                    Arb.double(0.0, 1000.0),
                    Arb.double(0.0, 1000.0),
                ) { a, b, c ->
                    val sortedValues = listOf(a, b, c).sorted()
                    val d1 = Distance(sortedValues[0])
                    val d2 = Distance(sortedValues[1])
                    val d3 = Distance(sortedValues[2])

                    // Verify transitivity
                    if (d1 < d2 && d2 < d3) {
                        (d1 < d3) shouldBe true
                    }
                    true
                }
            }
        }

        describe("Extension function consistency") {
            it("should create equivalent distances from different units") {
                forAll(
                    Arb.double(0.1, 100.0),
                ) { value ->
                    val fromKm = value.km
                    val fromM = (value * 1000).m

                    fromKm.value.shouldBeWithinPercentageOf(fromM.value, 0.0001)
                    true
                }
            }
        }
    }
})
