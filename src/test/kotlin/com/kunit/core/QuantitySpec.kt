package com.kunit.core

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.shouldBe

class QuantitySpec : DescribeSpec({
    describe("Quantity interface") {
        it("should store a value") {
            val quantity = TestQuantity(5.0)
            quantity.value shouldBe 5.0
        }

        it("should be comparable") {
            val quantity1 = TestQuantity(5.0)
            val quantity2 = TestQuantity(10.0)
            val quantity3 = TestQuantity(5.0)

            quantity1 shouldBeLessThan quantity2
            quantity2 shouldBeGreaterThan quantity1
            quantity1.compareTo(quantity3) shouldBe 0
        }

        it("should support arithmetic operations") {
            val quantity1 = TestQuantity(5.0)
            val quantity2 = TestQuantity(3.0)

            (quantity1 + quantity2).value shouldBe 8.0
            (quantity1 - quantity2).value shouldBe 2.0
            (quantity1 * 2.0).value shouldBe 10.0
            (quantity1 / 2.0).value shouldBe 2.5
        }

        it("should support unary operations") {
            val quantity = TestQuantity(5.0)

            (+quantity).value shouldBe 5.0
            (-quantity).value shouldBe -5.0
        }
    }
})

private class TestQuantity(override val value: Double) : Quantity<TestQuantity> {
    override fun compareTo(other: TestQuantity): Int = value.compareTo(other.value)

    override fun plus(other: TestQuantity) = TestQuantity(value + other.value)

    override fun minus(other: TestQuantity) = TestQuantity(value - other.value)

    override fun times(factor: Double) = TestQuantity(value * factor)

    override fun div(divisor: Double) = TestQuantity(value / divisor)

    override fun unaryPlus() = this

    override fun unaryMinus() = TestQuantity(-value)
}
