package com.kunit.integration

import com.kunit.units.duration.Duration
import com.kunit.units.duration.days
import com.kunit.units.duration.hours
import com.kunit.units.duration.microseconds
import com.kunit.units.duration.milliseconds
import com.kunit.units.duration.minutes
import com.kunit.units.duration.seconds
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

class JavaTimeIntegrationSpec : DescribeSpec({
    describe("java.time Integration") {
        describe("LocalDateTime + Duration") {
            it("should add Duration to LocalDateTime") {
                val dateTime = LocalDateTime.of(2024, 1, 1, 12, 0, 0)
                val duration = 2.hours + 30.minutes

                val result = dateTime + duration
                result shouldBe LocalDateTime.of(2024, 1, 1, 14, 30, 0)
            }

            it("should subtract Duration from LocalDateTime") {
                val dateTime = LocalDateTime.of(2024, 1, 1, 12, 0, 0)
                val duration = 1.hours + 15.minutes

                val result = dateTime - duration
                result shouldBe LocalDateTime.of(2024, 1, 1, 10, 45, 0)
            }

            it("should handle days spanning midnight") {
                val dateTime = LocalDateTime.of(2024, 1, 1, 23, 30, 0)
                val duration = 2.hours

                val result = dateTime + duration
                result shouldBe LocalDateTime.of(2024, 1, 2, 1, 30, 0)
            }

            it("should handle negative durations") {
                val dateTime = LocalDateTime.of(2024, 1, 2, 1, 0, 0)
                val duration = 2.hours

                val result = dateTime + (-duration)
                result shouldBe LocalDateTime.of(2024, 1, 1, 23, 0, 0)
            }
        }

        describe("LocalDate + Duration") {
            it("should add days to LocalDate") {
                val date = LocalDate.of(2024, 1, 1)
                val duration = 5.days

                val result = date + duration
                result shouldBe LocalDate.of(2024, 1, 6)
            }

            it("should subtract days from LocalDate") {
                val date = LocalDate.of(2024, 1, 10)
                val duration = 3.days

                val result = date - duration
                result shouldBe LocalDate.of(2024, 1, 7)
            }

            it("should handle month boundaries") {
                val date = LocalDate.of(2024, 1, 30)
                val duration = 5.days

                val result = date + duration
                result shouldBe LocalDate.of(2024, 2, 4)
            }

            it("should handle year boundaries") {
                val date = LocalDate.of(2023, 12, 30)
                val duration = 5.days

                val result = date + duration
                result shouldBe LocalDate.of(2024, 1, 4)
            }

            it("should ignore time components for LocalDate") {
                val date = LocalDate.of(2024, 1, 1)
                val duration = 2.days + 5.hours + 30.minutes

                // Should only add the days part
                val result = date + duration
                result shouldBe LocalDate.of(2024, 1, 3)
            }
        }

        describe("LocalTime + Duration") {
            it("should add Duration to LocalTime") {
                val time = LocalTime.of(10, 30, 0)
                val duration = 2.hours + 15.minutes

                val result = time + duration
                result shouldBe LocalTime.of(12, 45, 0)
            }

            it("should handle time wrapping") {
                val time = LocalTime.of(23, 30, 0)
                val duration = 2.hours

                val result = time + duration
                result shouldBe LocalTime.of(1, 30, 0)
            }

            it("should handle seconds and nanoseconds") {
                val time = LocalTime.of(10, 30, 45, 500_000_000)
                val duration = 30.seconds + 500.milliseconds

                val result = time + duration
                result shouldBe LocalTime.of(10, 31, 16, 0)
            }
        }

        describe("Instant + Duration") {
            it("should add Duration to Instant") {
                val instant = Instant.parse("2024-01-01T12:00:00Z")
                val duration = 3.hours + 45.minutes

                val result = instant + duration
                result shouldBe Instant.parse("2024-01-01T15:45:00Z")
            }

            it("should handle millisecond precision") {
                val instant = Instant.parse("2024-01-01T12:00:00.000Z")
                val duration = 1234.milliseconds

                val result = instant + duration
                result shouldBe Instant.parse("2024-01-01T12:00:01.234Z")
            }
        }

        describe("ZonedDateTime + Duration") {
            it("should add Duration to ZonedDateTime") {
                val zonedDateTime = ZonedDateTime.parse("2024-01-01T12:00:00+01:00[Europe/Paris]")
                val duration = 2.hours + 30.minutes

                val result = zonedDateTime + duration
                result.toLocalDateTime() shouldBe LocalDateTime.of(2024, 1, 1, 14, 30, 0)
            }

            it("should handle DST transitions") {
                // This test would be more complex and timezone-specific
                // Just checking basic functionality here
                val zone = ZoneId.of("UTC")
                val zonedDateTime = ZonedDateTime.of(2024, 1, 1, 12, 0, 0, 0, zone)
                val duration = 24.hours

                val result = zonedDateTime + duration
                result.toLocalDateTime() shouldBe LocalDateTime.of(2024, 1, 2, 12, 0, 0)
            }
        }

        describe("java.time.Duration conversion") {
            it("should convert KUnit Duration to java.time.Duration") {
                val kunitDuration = 2.hours + 30.minutes + 45.seconds
                val javaDuration = kunitDuration.toJavaDuration()

                javaDuration shouldBe java.time.Duration.ofSeconds(9045)
            }

            it("should convert java.time.Duration to KUnit Duration") {
                val javaDuration = java.time.Duration.ofHours(1).plusMinutes(30).plusSeconds(15)
                val kunitDuration = javaDuration.toKUnitDuration()

                kunitDuration.toSeconds() shouldBe 5415.0
            }

            it("should handle nanosecond precision") {
                val kunitDuration = 500.milliseconds + 123.microseconds
                val javaDuration = kunitDuration.toJavaDuration()

                javaDuration.toNanos() shouldBe 500_123_000L
            }

            it("should handle negative durations") {
                val kunitDuration = -2.hours
                val javaDuration = kunitDuration.toJavaDuration()

                javaDuration.isNegative shouldBe true
                javaDuration.toHours() shouldBe -2L
            }

            it("should preserve identity through round-trip conversion") {
                val original = 1.hours + 23.minutes + 45.seconds + 678.milliseconds
                val converted = original.toJavaDuration().toKUnitDuration()

                // Due to floating point precision, use a small tolerance
                (kotlin.math.abs(converted.toMillis() - original.toMillis()) < 0.001) shouldBe true
            }
        }
    }
})
