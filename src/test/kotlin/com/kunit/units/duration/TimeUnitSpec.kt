package com.kunit.units.duration

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class TimeUnitSpec : DescribeSpec({
    describe("TimeUnit") {
        describe("basic units") {
            it("should have nanosecond") {
                TimeUnit.NANOSECOND.symbol shouldBe "ns"
                TimeUnit.NANOSECOND.unitName shouldBe "nanosecond"
                TimeUnit.NANOSECOND.factor shouldBe 1e-9
            }

            it("should have microsecond") {
                TimeUnit.MICROSECOND.symbol shouldBe "μs"
                TimeUnit.MICROSECOND.unitName shouldBe "microsecond"
                TimeUnit.MICROSECOND.factor shouldBe 1e-6
            }

            it("should have millisecond") {
                TimeUnit.MILLISECOND.symbol shouldBe "ms"
                TimeUnit.MILLISECOND.unitName shouldBe "millisecond"
                TimeUnit.MILLISECOND.factor shouldBe 0.001
            }

            it("should have second as base unit") {
                TimeUnit.SECOND.symbol shouldBe "s"
                TimeUnit.SECOND.unitName shouldBe "second"
                TimeUnit.SECOND.factor shouldBe 1.0
            }

            it("should have minute") {
                TimeUnit.MINUTE.symbol shouldBe "min"
                TimeUnit.MINUTE.unitName shouldBe "minute"
                TimeUnit.MINUTE.factor shouldBe 60.0
            }

            it("should have hour") {
                TimeUnit.HOUR.symbol shouldBe "h"
                TimeUnit.HOUR.unitName shouldBe "hour"
                TimeUnit.HOUR.factor shouldBe 3600.0
            }

            it("should have day") {
                TimeUnit.DAY.symbol shouldBe "d"
                TimeUnit.DAY.unitName shouldBe "day"
                TimeUnit.DAY.factor shouldBe 86400.0
            }

            it("should have week") {
                TimeUnit.WEEK.symbol shouldBe "w"
                TimeUnit.WEEK.unitName shouldBe "week"
                TimeUnit.WEEK.factor shouldBe 604800.0
            }
        }
    }
})
