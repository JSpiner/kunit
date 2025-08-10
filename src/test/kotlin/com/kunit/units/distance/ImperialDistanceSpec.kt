package com.kunit.units.distance

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.matchers.shouldBe

class ImperialDistanceSpec : DescribeSpec({
    describe("Imperial Distance Units") {
        describe("DistanceUnit imperial values") {
            it("should have inch") {
                DistanceUnit.INCH.symbol shouldBe "in"
                DistanceUnit.INCH.unitName shouldBe "inch"
                DistanceUnit.INCH.factor shouldBe 0.0254
            }

            it("should have foot") {
                DistanceUnit.FOOT.symbol shouldBe "ft"
                DistanceUnit.FOOT.unitName shouldBe "foot"
                DistanceUnit.FOOT.factor shouldBe 0.3048
            }

            it("should have yard") {
                DistanceUnit.YARD.symbol shouldBe "yd"
                DistanceUnit.YARD.unitName shouldBe "yard"
                DistanceUnit.YARD.factor shouldBe 0.9144
            }

            it("should have mile") {
                DistanceUnit.MILE.symbol shouldBe "mi"
                DistanceUnit.MILE.unitName shouldBe "mile"
                DistanceUnit.MILE.factor shouldBe 1609.344
            }
        }

        describe("Imperial conversions") {
            it("should convert to inches") {
                val distance = Distance(1.0) // 1 meter
                distance.toInches().shouldBeWithinPercentageOf(39.3701, 0.01)
            }

            it("should convert to feet") {
                val distance = Distance(1.0) // 1 meter
                distance.toFeet().shouldBeWithinPercentageOf(3.28084, 0.01)
            }

            it("should convert to yards") {
                val distance = Distance(1.0) // 1 meter
                distance.toYards().shouldBeWithinPercentageOf(1.09361, 0.01)
            }

            it("should convert to miles") {
                val distance = Distance(1609.344) // 1 mile in meters
                distance.toMiles() shouldBe 1.0
            }
        }

        describe("Imperial extension functions") {
            it("should create Distance from inches") {
                val distance = 12.inches
                distance.toInches().shouldBeWithinPercentageOf(12.0, 0.01)
                distance.toFeet().shouldBeWithinPercentageOf(1.0, 0.01)
            }

            it("should create Distance from feet") {
                val distance = 3.feet
                distance.toFeet().shouldBeWithinPercentageOf(3.0, 0.01)
                distance.toYards().shouldBeWithinPercentageOf(1.0, 0.01)
            }

            it("should create Distance from yards") {
                val distance = 1760.yards
                distance.toYards() shouldBe 1760.0
                distance.toMiles() shouldBe 1.0
            }

            it("should create Distance from miles") {
                val distance = 1.miles
                distance.toMiles() shouldBe 1.0
                distance.toM() shouldBe 1609.344
            }

            it("should work with mixed units") {
                val total = 1.miles + 100.yards + 3.feet + 6.inches
                total.toM().shouldBeWithinPercentageOf(1701.8508, 0.01)
            }
        }
    }
})
