package com.ionspin.kotlin.bignum.decimal

import kotlin.test.Test
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

/**
 * Created by Ugljesa Jovanovic
 * ugljesa.jovanovic@ionspin.com
 * on 08-Oct-2020
 */
class BigDecimalDivisionTest {
    @Test
    fun testInfinitePrecisionDivision() {
        assertTrue {
            val a = 2.2.toBigDecimal()
            val b = 2.toBigDecimal()
            val result = a / b
            result == 1.1.toBigDecimal()
        }

        assertTrue {
            val a = 2.2.toBigDecimal()
            val b = 0.5.toBigDecimal()
            val result = a / b
            result == 4.4.toBigDecimal()
        }

        assertTrue {
            val a = 2000.212.toBigDecimal()
            val b = 2.toBigDecimal()
            val result = a / b
            result == 1000.106.toBigDecimal()
        }

        assertTrue {
            val a = 202020.toBigDecimal()
            val b = 10101.toBigDecimal()
            val result = a / b
            result == 20.toBigDecimal()
        }

        assertTrue {
            val a = 202020.toBigDecimal()
            val b = 20.toBigDecimal()
            val result = a / b
            result == 10101.toBigDecimal()
        }

        assertTrue {
            val a = 0.00000202020.toBigDecimal()
            val b = 20.toBigDecimal()
            val result = a / b
            result == 0.00000010101.toBigDecimal()
        }

        assertTrue {
            val a = 0.00000202020.toBigDecimal()
            val b = 0.020.toBigDecimal()
            val result = a / b
            result == 0.00010101.toBigDecimal()
        }

        assertTrue {
            val a = 10.toBigDecimal()
            val b = 20000000000000000.toBigDecimalUsingSignificandAndExponent(exponent = 0) // This is equal to 2.0000000000000000
            val result = a / b
            result == 5.toBigDecimal()
        }

        assertTrue {
            val a = 10000000000000000.toBigDecimalUsingSignificandAndExponent(exponent = 1) // This is equal to 10.000000000000000
            val b = 20000000000000000.toBigDecimalUsingSignificandAndExponent(exponent = 0) // This is equal to 2.0000000000000000
            val result = a / b
            result == 5.toBigDecimal()
        }

        assertTrue {
            val a = 10000000000000000.toBigDecimalUsingSignificandAndExponent(exponent = 1) // This is equal to 10.000000000000000
            val b = 2.toBigDecimal() // This is equal to 2.0000000000000000
            val result = a / b
            result == 5.toBigDecimal()
        }

        assertTrue {
            val first = BigDecimal.fromFloat(123.456f)
            val second = BigDecimal.fromFloat(2.0f)
            var bigDecimal = first.divide(second)
            bigDecimal == 61.728f.toBigDecimal()
        }

        assertTrue {
            val first = BigDecimal.fromFloat(123.456f)
            val second = BigDecimal.fromFloat(0.5f)
            var bigDecimal = first.divide(second)
            bigDecimal == 246.912f.toBigDecimal()
        }
    }

    @Test
    fun testInfinitePrecisionNonTerminating() {
        val a = 1.toBigDecimal()
        val b = 3.toBigDecimal()
        assertFailsWith<ArithmeticException> {
            val result = a / b
        }
    }
}
