package io.jspiner.units.mass

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MassUnitSpec : DescribeSpec({
    describe("MassUnit") {
        describe("metric units") {
            it("should have milligram") {
                MassUnit.MILLIGRAM.symbol shouldBe "mg"
                MassUnit.MILLIGRAM.unitName shouldBe "milligram"
                MassUnit.MILLIGRAM.factor shouldBe 0.001
            }

            it("should have gram as base unit") {
                MassUnit.GRAM.symbol shouldBe "g"
                MassUnit.GRAM.unitName shouldBe "gram"
                MassUnit.GRAM.factor shouldBe 1.0
            }

            it("should have kilogram") {
                MassUnit.KILOGRAM.symbol shouldBe "kg"
                MassUnit.KILOGRAM.unitName shouldBe "kilogram"
                MassUnit.KILOGRAM.factor shouldBe 1000.0
            }

            it("should have metric ton") {
                MassUnit.METRIC_TON.symbol shouldBe "t"
                MassUnit.METRIC_TON.unitName shouldBe "metric ton"
                MassUnit.METRIC_TON.factor shouldBe 1000000.0
            }
        }

        describe("imperial units") {
            it("should have ounce") {
                MassUnit.OUNCE.symbol shouldBe "oz"
                MassUnit.OUNCE.unitName shouldBe "ounce"
                MassUnit.OUNCE.factor shouldBe 28.349523125
            }

            it("should have pound") {
                MassUnit.POUND.symbol shouldBe "lb"
                MassUnit.POUND.unitName shouldBe "pound"
                MassUnit.POUND.factor shouldBe 453.59237
            }
        }
    }
})
