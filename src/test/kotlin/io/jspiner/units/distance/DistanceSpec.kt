package io.jspiner.units.distance

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.matchers.shouldBe

class DistanceSpec : DescribeSpec({
    describe("Distance") {
        describe("creation") {
            it("should store value in meters") {
                val distance = Distance(100.0)
                distance.value shouldBe 100.0
            }

            it("should be created with inline value class") {
                // This verifies that Distance is a value class
                val distance1 = Distance(50.0)
                val distance2 = Distance(50.0)
                (distance1 == distance2) shouldBe true
            }
        }

        describe("conversions") {
            it("should convert to meters") {
                val distance = Distance(1500.0)
                distance.toM() shouldBe 1500.0
            }

            it("should convert to kilometers") {
                val distance = Distance(1500.0)
                distance.toKm() shouldBe 1.5
            }

            it("should convert to centimeters") {
                val distance = Distance(1.5)
                distance.toCm() shouldBe 150.0
            }

            it("should convert to millimeters") {
                val distance = Distance(0.5)
                distance.toMm() shouldBe 500.0
            }

            it("should handle small values") {
                val distance = Distance(0.001)
                distance.toMm() shouldBe 1.0
                distance.toKm().shouldBeWithinPercentageOf(0.000001, 0.01)
            }

            it("should handle large values") {
                val distance = Distance(1000000.0)
                distance.toKm() shouldBe 1000.0
                distance.toMm() shouldBe 1000000000.0
            }
        }

        describe("toString") {
            it("should format with default unit") {
                val distance = Distance(100.0)
                distance.toString() shouldBe "100.0 m"
            }
        }

        describe("base unit") {
            it("should use meter as base unit") {
                val distance = Distance(100.0)
                distance.baseUnit shouldBe DistanceUnit.METER
            }
        }

        describe("convertTo") {
            it("should convert to any DistanceUnit") {
                val distance = Distance(1000.0)
                distance.convertTo(DistanceUnit.KILOMETER) shouldBe 1.0
                distance.convertTo(DistanceUnit.METER) shouldBe 1000.0
                distance.convertTo(DistanceUnit.CENTIMETER) shouldBe 100000.0
                distance.convertTo(DistanceUnit.MILLIMETER) shouldBe 1000000.0
            }
        }
    }
})
