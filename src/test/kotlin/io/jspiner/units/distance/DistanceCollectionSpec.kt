package io.jspiner.units.distance

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.doubles.shouldBeWithinPercentageOf
import io.kotest.matchers.shouldBe

class DistanceCollectionSpec : DescribeSpec({
    describe("Collection Operations") {
        describe("sum") {
            it("should sum a collection of distances") {
                val distances = listOf(1.m, 2.m, 3.m, 4.m, 5.m)
                distances.sum() shouldBe 15.m
            }

            it("should handle empty list") {
                val distances = emptyList<Distance>()
                distances.sum() shouldBe 0.m
            }

            it("should sum distances with different units") {
                val distances = listOf(1.km, 500.m, 200000.mm, 100000.cm)
                // 1000m + 500m + 200m + 1000m = 2700m
                distances.sum() shouldBe 2700.m
            }

            it("should work with Set") {
                val distances = setOf(1.m, 2.m, 3.m)
                distances.sum() shouldBe 6.m
            }

            it("should work with Array") {
                val distances = arrayOf(10.km, 5.km, 3.km)
                distances.sum() shouldBe 18.km
            }
        }

        describe("average") {
            it("should calculate average of distances") {
                val distances = listOf(2.m, 4.m, 6.m, 8.m)
                distances.average() shouldBe 5.m
            }

            it("should handle single element") {
                val distances = listOf(42.m)
                distances.average() shouldBe 42.m
            }

            it("should throw on empty list") {
                val distances = emptyList<Distance>()
                shouldThrow<IllegalArgumentException> {
                    distances.average()
                }
            }

            it("should average distances with different units") {
                val distances = listOf(1.km, 500.m, 1500.m)
                // (1000m + 500m + 1500m) / 3 = 1000m
                distances.average() shouldBe 1.km
            }

            it("should handle decimal averages") {
                val distances = listOf(1.m, 2.m, 3.m)
                val avg = distances.average()
                avg.value.shouldBeWithinPercentageOf(2.0, 0.001)
            }
        }

        describe("min/max") {
            it("should find minimum distance") {
                val distances = listOf(5.m, 2.m, 8.m, 1.m, 9.m)
                distances.min() shouldBe 1.m
                distances.minOrNull() shouldBe 1.m
            }

            it("should find maximum distance") {
                val distances = listOf(5.m, 2.m, 8.m, 1.m, 9.m)
                distances.max() shouldBe 9.m
                distances.maxOrNull() shouldBe 9.m
            }

            it("should work with different units") {
                val distances = listOf(1.km, 500.m, 2000.m, 100.m)
                distances.min() shouldBe 100.m
                distances.max() shouldBe 2000.m
            }

            it("should handle single element") {
                val distances = listOf(42.km)
                distances.min() shouldBe 42.km
                distances.max() shouldBe 42.km
            }

            it("should return null for empty list with minOrNull/maxOrNull") {
                val distances = emptyList<Distance>()
                distances.minOrNull() shouldBe null
                distances.maxOrNull() shouldBe null
            }

            it("should work with sorted") {
                val distances = listOf(5.m, 2.m, 8.m, 1.m, 9.m)
                val sorted = distances.sorted()
                sorted shouldBe listOf(1.m, 2.m, 5.m, 8.m, 9.m)
            }

            it("should work with sortedDescending") {
                val distances = listOf(5.m, 2.m, 8.m, 1.m, 9.m)
                val sorted = distances.sortedDescending()
                sorted shouldBe listOf(9.m, 8.m, 5.m, 2.m, 1.m)
            }
        }

        describe("minBy/maxBy") {
            it("should work with minBy") {
                data class Route(val name: String, val distance: Distance)

                val routes =
                    listOf(
                        Route("A", 5.km),
                        Route("B", 2.km),
                        Route("C", 8.km),
                    )

                routes.minBy { it.distance } shouldBe Route("B", 2.km)
            }

            it("should work with maxBy") {
                data class Route(val name: String, val distance: Distance)

                val routes =
                    listOf(
                        Route("A", 5.km),
                        Route("B", 2.km),
                        Route("C", 8.km),
                    )

                routes.maxBy { it.distance } shouldBe Route("C", 8.km)
            }
        }

        describe("filter operations") {
            it("should filter distances") {
                val distances = listOf(1.m, 5.m, 10.m, 15.m, 20.m)
                val filtered = distances.filter { it > 5.m }
                filtered shouldBe listOf(10.m, 15.m, 20.m)
            }

            it("should partition distances") {
                val distances = listOf(1.m, 5.m, 10.m, 15.m, 20.m)
                val (small, large) = distances.partition { it <= 10.m }
                small shouldBe listOf(1.m, 5.m, 10.m)
                large shouldBe listOf(15.m, 20.m)
            }
        }

        describe("sumOf") {
            it("should work with sumOf") {
                data class Route(val name: String, val distance: Distance)

                val routes =
                    listOf(
                        Route("A", 5.km),
                        Route("B", 2.km),
                        Route("C", 8.km),
                    )

                routes.sumOf { it.distance } shouldBe 15.km
            }
        }
    }
})
