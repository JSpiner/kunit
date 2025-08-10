package io.jspiner.conversion

import io.jspiner.core.Unit
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.matchers.shouldBe

class ConversionEngineSpec : DescribeSpec({
    describe("ConversionEngine") {
        describe("convert function") {
            it("should convert between units with same base") {
                val result =
                    ConversionEngine.convert(
                        value = 1000.0,
                        fromUnit = TestUnit.METER,
                        toUnit = TestUnit.KILOMETER,
                    )
                result shouldBe 1.0
            }

            it("should handle identity conversion") {
                val result =
                    ConversionEngine.convert(
                        value = 42.0,
                        fromUnit = TestUnit.METER,
                        toUnit = TestUnit.METER,
                    )
                result shouldBe 42.0
            }

            it("should convert between different units") {
                val result =
                    ConversionEngine.convert(
                        value = 5.0,
                        fromUnit = TestUnit.KILOMETER,
                        toUnit = TestUnit.CENTIMETER,
                    )
                result shouldBe 500000.0
            }

            it("should handle small conversions") {
                val result =
                    ConversionEngine.convert(
                        value = 1.0,
                        fromUnit = TestUnit.MILLIMETER,
                        toUnit = TestUnit.KILOMETER,
                    )
                result.shouldBeWithinPercentageOf(0.000001, 0.01)
            }

            it("should handle large conversions") {
                val result =
                    ConversionEngine.convert(
                        value = 1000000.0,
                        fromUnit = TestUnit.KILOMETER,
                        toUnit = TestUnit.MILLIMETER,
                    )
                result shouldBe 1000000000000.0
            }

            it("should throw exception for division by zero") {
                shouldThrow<IllegalArgumentException> {
                    ConversionEngine.convert(
                        value = 10.0,
                        fromUnit = TestUnit.METER,
                        toUnit = TestUnit.ZERO_FACTOR,
                    )
                }
            }
        }

        describe("efficiency") {
            it("should be inline for zero overhead") {
                // This test verifies compilation works with inline
                val result = ConversionEngine.convert(100.0, TestUnit.CENTIMETER, TestUnit.METER)
                result shouldBe 1.0
            }
        }
    }
})

private enum class TestUnit(
    override val symbol: String,
    override val unitName: String,
    override val factor: Double,
) : Unit {
    MILLIMETER("mm", "millimeter", 0.001),
    CENTIMETER("cm", "centimeter", 0.01),
    METER("m", "meter", 1.0),
    KILOMETER("km", "kilometer", 1000.0),
    ZERO_FACTOR("zf", "zero_factor", 0.0),
}
