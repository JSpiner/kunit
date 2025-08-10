package com.kunit.units.temperature

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TemperatureSpec : DescribeSpec({
    describe("Temperature") {
        describe("creation") {
            it("should store value in Celsius") {
                val temp = Temperature(25.0)
                temp.value shouldBe 25.0
            }

            it("should be created with inline value class") {
                val temp1 = Temperature(20.0)
                val temp2 = Temperature(20.0)
                (temp1 == temp2) shouldBe true
            }
        }

        describe("conversions") {
            it("should convert to Celsius") {
                val temp = Temperature(0.0)
                temp.toCelsius() shouldBe 0.0
            }

            it("should convert to Fahrenheit") {
                val temp = Temperature(0.0)
                temp.toFahrenheit() shouldBe 32.0

                val temp2 = Temperature(100.0)
                temp2.toFahrenheit() shouldBe 212.0
            }

            it("should convert to Kelvin") {
                val temp = Temperature(0.0)
                temp.toKelvin() shouldBe 273.15

                val temp2 = Temperature(100.0)
                temp2.toKelvin() shouldBe 373.15
            }
        }

        describe("comparison") {
            it("should support comparison operators") {
                val t1 = Temperature(10.0)
                val t2 = Temperature(20.0)
                val t3 = Temperature(10.0)

                (t1 < t2) shouldBe true
                (t2 > t1) shouldBe true
                (t1 <= t3) shouldBe true
                (t1 >= t3) shouldBe true
                (t1 == t3) shouldBe true
            }
        }

        describe("toString") {
            it("should format with default unit") {
                val temp = Temperature(25.0)
                temp.toString() shouldBe "25.0 °C"
            }
        }

        describe("base unit") {
            it("should use Celsius as base unit") {
                val temp = Temperature(25.0)
                temp.baseUnit shouldBe TemperatureUnit.CELSIUS
            }
        }
    }

    describe("TemperatureDifference") {
        describe("creation") {
            it("should store difference value") {
                val diff = TemperatureDifference(5.0)
                diff.value shouldBe 5.0
            }
        }

        describe("temperature difference operations") {
            it("should calculate difference between temperatures") {
                val morning = Temperature(15.0)
                val afternoon = Temperature(25.0)
                val diff = afternoon - morning

                diff.value shouldBe 10.0
            }

            it("should add difference to temperature") {
                val morning = Temperature(15.0)
                val diff = TemperatureDifference(10.0)
                val afternoon = morning + diff

                afternoon.value shouldBe 25.0
            }

            it("should subtract difference from temperature") {
                val afternoon = Temperature(25.0)
                val diff = TemperatureDifference(10.0)
                val morning = afternoon - diff

                morning.value shouldBe 15.0
            }
        }

        describe("difference arithmetic") {
            it("should support addition of differences") {
                val diff1 = TemperatureDifference(5.0)
                val diff2 = TemperatureDifference(3.0)
                val total = diff1 + diff2

                total.value shouldBe 8.0
            }

            it("should support subtraction of differences") {
                val diff1 = TemperatureDifference(5.0)
                val diff2 = TemperatureDifference(3.0)
                val result = diff1 - diff2

                result.value shouldBe 2.0
            }

            it("should support multiplication") {
                val diff = TemperatureDifference(5.0)
                val result = diff * 2.0

                result.value shouldBe 10.0
            }

            it("should support division") {
                val diff = TemperatureDifference(10.0)
                val result = diff / 2.0

                result.value shouldBe 5.0
            }
        }
    }
})
