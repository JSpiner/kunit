package io.jspiner.integration

import io.jspiner.units.duration.hours
import io.jspiner.units.duration.milliseconds
import io.jspiner.units.duration.minutes
import io.jspiner.units.duration.seconds
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.matchers.shouldBe
import kotlin.time.Duration as KotlinDuration

class KotlinTimeIntegrationSpec : DescribeSpec({
    describe("kotlin.time.Duration integration") {
        describe("conversion from KUnit Duration to kotlin.time.Duration") {
            it("should convert seconds") {
                val kunitDuration = 5.seconds
                val kotlinDuration = kunitDuration.toKotlinDuration()

                kotlinDuration.inWholeSeconds shouldBe 5
            }

            it("should convert minutes") {
                val kunitDuration = 2.5.minutes
                val kotlinDuration = kunitDuration.toKotlinDuration()

                kotlinDuration.inWholeMinutes shouldBe 2
                kotlinDuration.inWholeSeconds shouldBe 150
            }

            it("should convert hours") {
                val kunitDuration = 1.5.hours
                val kotlinDuration = kunitDuration.toKotlinDuration()

                kotlinDuration.inWholeHours shouldBe 1
                kotlinDuration.inWholeMinutes shouldBe 90
            }

            it("should convert milliseconds") {
                val kunitDuration = 500.milliseconds
                val kotlinDuration = kunitDuration.toKotlinDuration()

                kotlinDuration.inWholeMilliseconds shouldBe 500
            }
        }

        describe("conversion from kotlin.time.Duration to KUnit Duration") {
            it("should convert from kotlin seconds") {
                val kotlinDuration = KotlinDuration.parse("5s")
                val kunitDuration = kotlinDuration.toKUnitDuration()

                kunitDuration.toSeconds() shouldBe 5.0
            }

            it("should convert from kotlin minutes") {
                val kotlinDuration = KotlinDuration.parse("2m30s")
                val kunitDuration = kotlinDuration.toKUnitDuration()

                kunitDuration.toSeconds() shouldBe 150.0
            }

            it("should convert from kotlin hours") {
                val kotlinDuration = KotlinDuration.parse("1h30m")
                val kunitDuration = kotlinDuration.toKUnitDuration()

                kunitDuration.toMinutes() shouldBe 90.0
            }
        }

        describe("round-trip conversion") {
            it("should maintain value in round-trip conversion") {
                val original = 123.456.seconds
                val kotlinDuration = original.toKotlinDuration()
                val backToKUnit = kotlinDuration.toKUnitDuration()

                backToKUnit.toSeconds().shouldBeWithinPercentageOf(123.456, 0.01)
            }
        }
    }
})
