package io.jspiner.units.temperature

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TemperatureUnitSpec : DescribeSpec({
    describe("TemperatureUnit") {
        it("should have Celsius") {
            TemperatureUnit.CELSIUS.symbol shouldBe "°C"
            TemperatureUnit.CELSIUS.unitName shouldBe "Celsius"
        }

        it("should have Fahrenheit") {
            TemperatureUnit.FAHRENHEIT.symbol shouldBe "°F"
            TemperatureUnit.FAHRENHEIT.unitName shouldBe "Fahrenheit"
        }

        it("should have Kelvin") {
            TemperatureUnit.KELVIN.symbol shouldBe "K"
            TemperatureUnit.KELVIN.unitName shouldBe "Kelvin"
        }
    }
})
