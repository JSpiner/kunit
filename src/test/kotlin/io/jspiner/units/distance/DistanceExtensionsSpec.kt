package io.jspiner.units.distance

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.matchers.shouldBe

class DistanceExtensionsSpec : DescribeSpec({
    describe("Distance Extension Functions") {
        describe("Number extensions") {
            it("should create Distance from km") {
                val distance = 5.km
                distance.toM() shouldBe 5000.0
                distance.toKm() shouldBe 5.0
            }

            it("should create Distance from m") {
                val distance = 100.m
                distance.toM() shouldBe 100.0
                distance.toKm() shouldBe 0.1
            }

            it("should create Distance from cm") {
                val distance = 250.cm
                distance.toCm() shouldBe 250.0
                distance.toM() shouldBe 2.5
            }

            it("should create Distance from mm") {
                val distance = 1500.mm
                distance.toMm() shouldBe 1500.0
                distance.toM() shouldBe 1.5
            }
        }

        describe("Int extensions") {
            it("should work with Int values") {
                val distance1 = 5.km
                distance1.toKm() shouldBe 5.0

                val distance2 = 100.m
                distance2.toM() shouldBe 100.0

                val distance3 = 50.cm
                distance3.toCm() shouldBe 50.0

                val distance4 = 25.mm
                distance4.toMm() shouldBe 25.0
            }
        }

        describe("Double extensions") {
            it("should work with Double values") {
                val distance1 = 5.5.km
                distance1.toKm() shouldBe 5.5

                val distance2 = 100.5.m
                distance2.toM() shouldBe 100.5

                val distance3 = 50.25.cm
                distance3.toCm().shouldBeWithinPercentageOf(50.25, 0.01)

                val distance4 = 25.75.mm
                distance4.toMm().shouldBeWithinPercentageOf(25.75, 0.01)
            }
        }

        describe("chaining operations") {
            it("should support arithmetic with extension functions") {
                val total = 5.km + 300.m + 50.cm
                total.toM() shouldBe 5300.5

                val difference = 10.km - 500.m
                difference.toM() shouldBe 9500.0

                val multiplied = (2.km) * 3.0
                multiplied.toM() shouldBe 6000.0

                val divided = (10.km) / 2.0
                divided.toKm() shouldBe 5.0
            }
        }
    }
})
