package io.jspiner.units.duration

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.matchers.shouldBe

class DurationSpec : DescribeSpec({
    describe("Duration") {
        describe("creation") {
            it("should store value in seconds") {
                val duration = Duration(100.0)
                duration.value shouldBe 100.0
            }

            it("should be created with inline value class") {
                val duration1 = Duration(50.0)
                val duration2 = Duration(50.0)
                (duration1 == duration2) shouldBe true
            }
        }

        describe("conversions") {
            it("should convert to nanoseconds") {
                val duration = Duration(0.001)
                duration.toNanoseconds().shouldBeWithinPercentageOf(1_000_000.0, 0.01)
            }

            it("should convert to microseconds") {
                val duration = Duration(0.001)
                duration.toMicroseconds() shouldBe 1000.0
            }

            it("should convert to milliseconds") {
                val duration = Duration(1.5)
                duration.toMilliseconds() shouldBe 1500.0
            }

            it("should convert to seconds") {
                val duration = Duration(60.0)
                duration.toSeconds() shouldBe 60.0
            }

            it("should convert to minutes") {
                val duration = Duration(120.0)
                duration.toMinutes() shouldBe 2.0
            }

            it("should convert to hours") {
                val duration = Duration(7200.0)
                duration.toHours() shouldBe 2.0
            }

            it("should convert to days") {
                val duration = Duration(86400.0)
                duration.toDays() shouldBe 1.0
            }

            it("should convert to weeks") {
                val duration = Duration(604800.0)
                duration.toWeeks() shouldBe 1.0
            }
        }

        describe("arithmetic operations") {
            it("should support addition") {
                val d1 = Duration(30.0)
                val d2 = Duration(15.0)
                (d1 + d2).value shouldBe 45.0
            }

            it("should support subtraction") {
                val d1 = Duration(30.0)
                val d2 = Duration(15.0)
                (d1 - d2).value shouldBe 15.0
            }

            it("should support multiplication") {
                val duration = Duration(10.0)
                (duration * 3.0).value shouldBe 30.0
            }

            it("should support division") {
                val duration = Duration(30.0)
                (duration / 3.0).value shouldBe 10.0
            }
        }

        describe("comparison") {
            it("should support comparison operators") {
                val d1 = Duration(10.0)
                val d2 = Duration(20.0)
                val d3 = Duration(10.0)

                (d1 < d2) shouldBe true
                (d2 > d1) shouldBe true
                (d1 <= d3) shouldBe true
                (d1 >= d3) shouldBe true
                (d1 == d3) shouldBe true
            }
        }

        describe("toString") {
            it("should format with default unit") {
                val duration = Duration(100.0)
                duration.toString() shouldBe "100.0 s"
            }
        }

        describe("base unit") {
            it("should use second as base unit") {
                val duration = Duration(100.0)
                duration.baseUnit shouldBe TimeUnit.SECOND
            }
        }
    }
})
