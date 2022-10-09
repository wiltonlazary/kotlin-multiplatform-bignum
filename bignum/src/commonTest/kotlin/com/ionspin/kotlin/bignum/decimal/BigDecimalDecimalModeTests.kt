package com.ionspin.kotlin.bignum.decimal

import com.ionspin.kotlin.bignum.runBlockingTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BigDecimalDecimalModeTests {

    @Test
    fun testInvalidPrecision() {
        assertFailsWith(ArithmeticException::class) {
            1.toBigDecimal(decimalMode = DecimalMode(-1))
        }
    }

    @Test
    fun testModePreservation() = runBlockingTest {
        run {
            val a = BigDecimal.fromInt(1, DecimalMode(scale = 5, roundingMode = RoundingMode.AWAY_FROM_ZERO))
            val negated = -a
            assertEquals(a.decimalMode, negated.decimalMode)
        }

        run {
            val a = BigDecimal.fromInt(1, DecimalMode(scale = 5, roundingMode = RoundingMode.AWAY_FROM_ZERO))
            val absolute = a.abs()
            assertEquals(a.decimalMode, absolute.decimalMode)
        }

        run {
            val a = BigDecimal.fromInt(1, DecimalMode(scale = 5, roundingMode = RoundingMode.AWAY_FROM_ZERO))
            val negated = a.negate()
            assertEquals(a.decimalMode, negated.decimalMode)
        }
    }
}
