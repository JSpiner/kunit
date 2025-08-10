package io.jspiner.units.distance

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe

class DistanceRangeSpec : DescribeSpec({
    describe("DistanceRange") {
        describe("creation") {
            it("should create range with .. operator") {
                val range = 1.m..10.m
                range.start shouldBe 1.m
                range.endInclusive shouldBe 10.m
            }

            it("should create range with rangeTo") {
                val range = 5.km.rangeTo(10.km)
                range.start shouldBe 5.km
                range.endInclusive shouldBe 10.km
            }

            it("should support different units") {
                val range = 100.cm..5.m
                range.start shouldBe 100.cm
                range.endInclusive shouldBe 5.m
            }
        }

        describe("contains") {
            it("should check if value is in range") {
                val range = 1.m..10.m

                (5.m in range).shouldBeTrue()
                (1.m in range).shouldBeTrue()
                (10.m in range).shouldBeTrue()
                (0.m in range).shouldBeFalse()
                (11.m in range).shouldBeFalse()
            }

            it("should work with different units") {
                val range = 1.m..10.m

                (500.cm in range).shouldBeTrue()
                (5000.mm in range).shouldBeTrue()
                (0.001.km in range).shouldBeTrue()
                (11000.mm in range).shouldBeFalse()
            }

            it("should support empty range") {
                val range = 10.m..1.m

                (5.m in range).shouldBeFalse()
                (1.m in range).shouldBeFalse()
                (10.m in range).shouldBeFalse()
            }
        }

        describe("isEmpty") {
            it("should detect empty ranges") {
                val emptyRange = 10.m..1.m
                val nonEmptyRange = 1.m..10.m

                emptyRange.isEmpty().shouldBeTrue()
                nonEmptyRange.isEmpty().shouldBeFalse()
            }

            it("should handle equal start and end") {
                val range = 5.m..5.m
                range.isEmpty().shouldBeFalse()
                (5.m in range).shouldBeTrue()
            }
        }
    }

    describe("DistanceProgression") {
        describe("creation") {
            it("should create progression with step") {
                val progression = (0.m..10.m step 2.m)
                val list = progression.toList()

                list shouldContainExactly listOf(0.m, 2.m, 4.m, 6.m, 8.m, 10.m)
            }

            it("should work with different unit steps") {
                val progression = (0.m..5.m step 100.cm)
                val list = progression.toList()

                list shouldHaveSize 6
                list.first() shouldBe 0.m
                list.last() shouldBe 5.m
            }

            it("should support reverse progression") {
                val progression = (10.m downTo 0.m step 2.m)
                val list = progression.toList()

                list shouldContainExactly listOf(10.m, 8.m, 6.m, 4.m, 2.m, 0.m)
            }

            it("should handle non-exact steps") {
                val progression = (0.m..10.m step 3.m)
                val list = progression.toList()

                list shouldContainExactly listOf(0.m, 3.m, 6.m, 9.m)
                list shouldNotContain 10.m // Because 9 + 3 > 10
            }
        }

        describe("iteration") {
            it("should be iterable") {
                val progression = (0.m..6.m step 2.m)
                val result = mutableListOf<Distance>()

                for (distance in progression) {
                    result.add(distance)
                }

                result shouldContainExactly listOf(0.m, 2.m, 4.m, 6.m)
            }

            it("should support forEach") {
                val progression = (0.km..3.km step 1.km)
                val result = mutableListOf<Distance>()

                progression.forEach { result.add(it) }

                result shouldContainExactly listOf(0.km, 1.km, 2.km, 3.km)
            }

            it("should support map operations") {
                val progression = (0.m..4.m step 2.m)
                val doubled = progression.map { it * 2.0 }

                doubled shouldContainExactly listOf(0.m, 4.m, 8.m)
            }
        }

        describe("contains") {
            it("should check if value is in progression") {
                val progression = (0.m..10.m step 2.m)

                (4.m in progression).shouldBeTrue()
                (5.m in progression).shouldBeFalse()
                (10.m in progression).shouldBeTrue()
                (12.m in progression).shouldBeFalse()
            }

            it("should work with different units in contains check") {
                val progression = (0.m..10.m step 2.m)

                (400.cm in progression).shouldBeTrue()
                (4000.mm in progression).shouldBeTrue()
                (500.cm in progression).shouldBeFalse()
            }
        }

        describe("properties") {
            it("should expose first, last, and step") {
                val progression = (1.km..10.km step 2.km)

                progression.first shouldBe 1.km
                progression.last shouldBe 9.km // Last actual value, not endInclusive
                progression.step shouldBe 2.km
            }

            it("should handle empty progression") {
                val progression = (10.m..1.m step 1.m)
                progression.isEmpty().shouldBeTrue()
                progression.toList().shouldHaveSize(0)
            }
        }
    }

    describe("extension functions") {
        describe("until") {
            it("should create half-open range") {
                val range = 1.m until 10.m

                (1.m in range).shouldBeTrue()
                (9.999.m in range).shouldBeTrue()
                (10.m in range).shouldBeFalse()
            }
        }

        describe("coerceIn") {
            it("should clamp value to range") {
                val range = 1.m..10.m

                0.m.coerceIn(range) shouldBe 1.m
                5.m.coerceIn(range) shouldBe 5.m
                15.m.coerceIn(range) shouldBe 10.m
            }

            it("should work with different units") {
                val range = 1.m..10.m

                500.cm.coerceIn(range) shouldBe 500.cm
                50.cm.coerceIn(range) shouldBe 1.m
                20.m.coerceIn(range) shouldBe 10.m
            }
        }

        describe("reversed") {
            it("should reverse progression") {
                val progression = (0.m..6.m step 2.m)
                val reversed = progression.reversed()

                reversed.toList() shouldContainExactly listOf(6.m, 4.m, 2.m, 0.m)
            }
        }
    }
})
