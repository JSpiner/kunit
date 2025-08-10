package io.jspiner.core

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class ConvertibleSpec : DescribeSpec({
    describe("Convertible interface") {
        it("should convert to different units") {
            val convertible = TestConvertible(1000.0)

            convertible.convertTo(TestUnit.METER) shouldBe 1000.0
            convertible.convertTo(TestUnit.KILOMETER) shouldBe 1.0
            convertible.convertTo(TestUnit.CENTIMETER) shouldBe 100000.0
        }

        it("should have a base unit") {
            val convertible = TestConvertible(100.0)
            convertible.baseUnit shouldBe TestUnit.METER
        }
    }
})

private enum class TestUnit(
    override val symbol: String,
    override val unitName: String,
    override val factor: Double,
) : Unit {
    METER("m", "meter", 1.0),
    KILOMETER("km", "kilometer", 1000.0),
    CENTIMETER("cm", "centimeter", 0.01),
}

private class TestConvertible(private val meters: Double) : Convertible<TestUnit> {
    override val baseUnit = TestUnit.METER

    override fun convertTo(unit: TestUnit): Double {
        return meters / unit.factor
    }
}
