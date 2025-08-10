package io.jspiner.units.mass

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MassSpec : DescribeSpec({
    describe("Mass") {
        describe("creation") {
            it("should store value in grams") {
                val mass = Mass(100.0)
                mass.value shouldBe 100.0
            }

            it("should be created with inline value class") {
                val mass1 = Mass(50.0)
                val mass2 = Mass(50.0)
                (mass1 == mass2) shouldBe true
            }
        }

        describe("conversions") {
            it("should convert to milligrams") {
                val mass = Mass(1.5)
                mass.toMilligrams() shouldBe 1500.0
            }

            it("should convert to grams") {
                val mass = Mass(1000.0)
                mass.toGrams() shouldBe 1000.0
            }

            it("should convert to kilograms") {
                val mass = Mass(1500.0)
                mass.toKilograms() shouldBe 1.5
            }

            it("should convert to metric tons") {
                val mass = Mass(2000000.0)
                mass.toMetricTons() shouldBe 2.0
            }

            it("should convert to ounces") {
                val mass = Mass(28.349523125)
                mass.toOunces() shouldBe 1.0
            }

            it("should convert to pounds") {
                val mass = Mass(453.59237)
                mass.toPounds() shouldBe 1.0
            }
        }

        describe("arithmetic operations") {
            it("should support addition") {
                val m1 = Mass(30.0)
                val m2 = Mass(15.0)
                (m1 + m2).value shouldBe 45.0
            }

            it("should support subtraction") {
                val m1 = Mass(30.0)
                val m2 = Mass(15.0)
                (m1 - m2).value shouldBe 15.0
            }

            it("should support multiplication") {
                val mass = Mass(10.0)
                (mass * 3.0).value shouldBe 30.0
            }

            it("should support division") {
                val mass = Mass(30.0)
                (mass / 3.0).value shouldBe 10.0
            }
        }

        describe("comparison") {
            it("should support comparison operators") {
                val m1 = Mass(10.0)
                val m2 = Mass(20.0)
                val m3 = Mass(10.0)

                (m1 < m2) shouldBe true
                (m2 > m1) shouldBe true
                (m1 <= m3) shouldBe true
                (m1 >= m3) shouldBe true
                (m1 == m3) shouldBe true
            }
        }

        describe("toString") {
            it("should format with default unit") {
                val mass = Mass(100.0)
                mass.toString() shouldBe "100.0 g"
            }
        }

        describe("base unit") {
            it("should use gram as base unit") {
                val mass = Mass(100.0)
                mass.baseUnit shouldBe MassUnit.GRAM
            }
        }
    }
})
