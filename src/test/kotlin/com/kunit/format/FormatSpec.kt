package com.kunit.format

import com.kunit.units.distance.*
import com.kunit.units.duration.*
import com.kunit.units.mass.*
import com.kunit.units.temperature.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import java.util.Locale

class FormatSpec : DescribeSpec({
    describe("Format Interface") {
        describe("default formatting") {
            it("should format Distance with default format") {
                val distance = 1234.567.m
                distance.format() shouldBe "1234.567 m"
            }

            it("should format Duration with default format") {
                val duration = 90.seconds
                duration.format() shouldBe "90.0 s"
            }

            it("should format Mass with default format") {
                val mass = 2500.g
                mass.format() shouldBe "2500.0 g"
            }

            it("should format Temperature with default format") {
                val temp = Temperature(25.5)
                temp.format() shouldBe "25.5 °C"
            }
        }

        describe("format with specific unit") {
            it("should format Distance in specific unit") {
                val distance = 1500.m
                distance.format(DistanceUnit.KILOMETER) shouldBe "1.5 km"
                distance.format(DistanceUnit.METER) shouldBe "1500.0 m"
                distance.format(DistanceUnit.CENTIMETER) shouldBe "150000.0 cm"
            }

            it("should format Duration in specific unit") {
                val duration = 90.seconds
                duration.format(TimeUnit.MINUTE) shouldBe "1.5 min"
                duration.format(TimeUnit.SECOND) shouldBe "90.0 s"
                duration.format(TimeUnit.MILLISECOND) shouldBe "90000.0 ms"
            }

            it("should format Mass in specific unit") {
                val mass = 2500.g
                mass.format(MassUnit.KILOGRAM) shouldBe "2.5 kg"
                mass.format(MassUnit.GRAM) shouldBe "2500.0 g"
                mass.format(MassUnit.MILLIGRAM) shouldBe "2500000.0 mg"
            }
        }
    }

    describe("DecimalFormat") {
        describe("decimal places") {
            it("should format with specified decimal places") {
                val distance = 1234.56789.m
                val formatter = DecimalFormat(decimalPlaces = 2)

                distance.format(formatter) shouldBe "1234.57 m"
            }

            it("should round correctly") {
                val distance = 1234.444.m
                val formatter2 = DecimalFormat(decimalPlaces = 2)
                val formatter1 = DecimalFormat(decimalPlaces = 1)
                val formatter0 = DecimalFormat(decimalPlaces = 0)

                distance.format(formatter2) shouldBe "1234.44 m"
                distance.format(formatter1) shouldBe "1234.4 m"
                distance.format(formatter0) shouldBe "1234 m"
            }

            it("should handle zero decimal places") {
                val distance = 1234.9.m
                val formatter = DecimalFormat(decimalPlaces = 0)

                distance.format(formatter) shouldBe "1235 m"
            }
        }

        describe("thousands separator") {
            it("should add thousands separator") {
                val distance = 1234567.89.m
                val formatter =
                    DecimalFormat(
                        decimalPlaces = 2,
                        useThousandsSeparator = true,
                    )

                distance.format(formatter) shouldBe "1,234,567.89 m"
            }

            it("should work without thousands separator") {
                val distance = 1234567.89.m
                val formatter =
                    DecimalFormat(
                        decimalPlaces = 2,
                        useThousandsSeparator = false,
                    )

                distance.format(formatter) shouldBe "1234567.89 m"
            }
        }

        describe("unit display") {
            it("should show unit symbol") {
                val distance = 5.km
                val formatter =
                    DecimalFormat(
                        decimalPlaces = 1,
                        showUnitSymbol = true,
                    )

                distance.format(formatter, DistanceUnit.KILOMETER) shouldBe "5.0 km"
            }

            it("should hide unit symbol") {
                val distance = 5.km
                val formatter =
                    DecimalFormat(
                        decimalPlaces = 1,
                        showUnitSymbol = false,
                    )

                distance.format(formatter, DistanceUnit.KILOMETER) shouldBe "5.0"
            }
        }

        describe("sign display") {
            it("should always show sign") {
                val positive = 5.m
                val negative = (-5).m
                val formatter =
                    DecimalFormat(
                        decimalPlaces = 1,
                        alwaysShowSign = true,
                    )

                positive.format(formatter) shouldBe "+5.0 m"
                negative.format(formatter) shouldBe "-5.0 m"
            }

            it("should only show negative sign") {
                val positive = 5.m
                val negative = (-5).m
                val formatter =
                    DecimalFormat(
                        decimalPlaces = 1,
                        alwaysShowSign = false,
                    )

                positive.format(formatter) shouldBe "5.0 m"
                negative.format(formatter) shouldBe "-5.0 m"
            }
        }

        describe("scientific notation") {
            it("should format in scientific notation") {
                val distance = 1234567.89.m
                val formatter =
                    DecimalFormat(
                        decimalPlaces = 2,
                        useScientificNotation = true,
                    )

                distance.format(formatter) shouldBe "1.23e6 m"
            }

            it("should format small numbers in scientific notation") {
                val distance = 0.0000123.m
                val formatter =
                    DecimalFormat(
                        decimalPlaces = 2,
                        useScientificNotation = true,
                    )

                distance.format(formatter) shouldBe "1.23e-5 m"
            }
        }
    }

    describe("Locale-based formatting") {
        describe("number formatting") {
            it("should format with US locale") {
                val distance = 1234.56.m
                val formatter = LocaleFormat(Locale.US)

                distance.format(formatter) shouldBe "1,234.56 m"
            }

            it("should format with German locale") {
                val distance = 1234.56.m
                val formatter = LocaleFormat(Locale.GERMANY)

                distance.format(formatter) shouldBe "1.234,56 m"
            }

            it("should format with French locale") {
                val distance = 1234.56.m
                val formatter = LocaleFormat(Locale.FRANCE)

                // French uses space as thousands separator
                val result = distance.format(formatter)
                // Check that it uses comma as decimal separator
                result.contains(",56 m").shouldBeTrue()
                // Check it has thousands separator (space, could be regular or non-breaking)
                (result.contains("1 234") || result.contains("1\u00A0234") || result.contains("1\u202F234")).shouldBeTrue()
            }
        }

        describe("unit names") {
            it("should use localized unit names") {
                val distance = 5.km
                val formatterEn = LocaleFormat(Locale.US, useFullUnitName = true)
                val formatterDe = LocaleFormat(Locale.GERMANY, useFullUnitName = true)

                distance.format(formatterEn, DistanceUnit.KILOMETER) shouldBe "5 kilometers"
                distance.format(formatterDe, DistanceUnit.KILOMETER) shouldBe "5 Kilometer"
            }
        }

        describe("temperature units") {
            it("should format temperature with locale") {
                val temp = Temperature(25.5)
                val formatterUs = LocaleFormat(Locale.US)
                val formatterDe = LocaleFormat(Locale.GERMANY)

                temp.format(formatterUs) shouldBe "25.5 °C"
                temp.format(formatterDe) shouldBe "25,5 °C"
            }
        }
    }

    describe("Custom format patterns") {
        it("should support custom format patterns") {
            val distance = 1234.567.m
            val formatter = PatternFormat("##,##0.00")

            distance.format(formatter) shouldBe "1,234.57 m"
        }

        it("should support pattern with no decimal places") {
            val distance = 1234.567.m
            val formatter = PatternFormat("##,##0")

            distance.format(formatter) shouldBe "1,235 m"
        }

        it("should support pattern with many decimal places") {
            val distance = 1234.5.m
            val formatter = PatternFormat("#.000000")

            distance.format(formatter) shouldBe "1234.500000 m"
        }
    }

    describe("Extension functions") {
        it("should format with precision extension") {
            val distance = 1234.56789.m

            distance.formatWithPrecision(2) shouldBe "1234.57 m"
            distance.formatWithPrecision(0) shouldBe "1235 m"
            distance.formatWithPrecision(4) shouldBe "1234.5679 m"
        }

        it("should format compact") {
            val distance = 1500.m
            distance.formatCompact() shouldBe "1.5 km"

            val smallDistance = 0.5.m
            smallDistance.formatCompact() shouldBe "50 cm"

            val tinyDistance = 0.002.m
            tinyDistance.formatCompact() shouldBe "2 mm"
        }

        it("should format duration compact") {
            val duration = 90.seconds
            duration.formatCompact() shouldBe "1.5 min"

            val longDuration = 3661.seconds
            longDuration.formatCompact() shouldBe "1 h 1 min 1 s"
        }
    }
})
