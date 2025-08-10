package com.kunit.core

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class UnitSpec : DescribeSpec({
    describe("Unit interface") {
        it("should have a symbol property") {
            val testUnit =
                object : Unit {
                    override val symbol: String = "m"
                    override val unitName: String = "meter"
                    override val factor: Double = 1.0
                }

            testUnit.symbol shouldBe "m"
            testUnit.symbol shouldNotBe ""
        }

        it("should have a unitName property") {
            val testUnit =
                object : Unit {
                    override val symbol: String = "kg"
                    override val unitName: String = "kilogram"
                    override val factor: Double = 1.0
                }

            testUnit.unitName shouldBe "kilogram"
            testUnit.unitName shouldNotBe ""
        }

        it("should have a conversion factor") {
            val testUnit =
                object : Unit {
                    override val symbol: String = "km"
                    override val unitName: String = "kilometer"
                    override val factor: Double = 1000.0
                }

            testUnit.factor shouldBe 1000.0
        }
    }
})
