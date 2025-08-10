package io.jspiner

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class DummyTest : DescribeSpec({
    describe("Dummy test") {
        it("should pass") {
            1 + 1 shouldBe 2
        }
    }
})
