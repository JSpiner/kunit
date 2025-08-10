package com.kunit.units.distance

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DistanceUnitSpec : DescribeSpec({
    describe("DistanceUnit") {
        describe("basic units") {
            it("should have millimeter") {
                DistanceUnit.MILLIMETER.symbol shouldBe "mm"
                DistanceUnit.MILLIMETER.unitName shouldBe "millimeter"
                DistanceUnit.MILLIMETER.factor shouldBe 0.001
            }

            it("should have centimeter") {
                DistanceUnit.CENTIMETER.symbol shouldBe "cm"
                DistanceUnit.CENTIMETER.unitName shouldBe "centimeter"
                DistanceUnit.CENTIMETER.factor shouldBe 0.01
            }

            it("should have meter as base unit") {
                DistanceUnit.METER.symbol shouldBe "m"
                DistanceUnit.METER.unitName shouldBe "meter"
                DistanceUnit.METER.factor shouldBe 1.0
            }

            it("should have kilometer") {
                DistanceUnit.KILOMETER.symbol shouldBe "km"
                DistanceUnit.KILOMETER.unitName shouldBe "kilometer"
                DistanceUnit.KILOMETER.factor shouldBe 1000.0
            }
        }
    }
})
