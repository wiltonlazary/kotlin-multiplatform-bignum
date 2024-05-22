/*
 *    Copyright 2019 Ugljesa Jovanovic
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.ionspin.kotlin.bignum.integer.arithmetic

import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Created by Ugljesa Jovanovic
 * ugljesa.jovanovic@ionspin.com
 * on 23-Mar-2019
 */

class BitwiseTest {

    @Test
    fun testNegate() {
        val bigInt = BigInteger.fromInt(123)
        val negatedBigInt = -bigInt
        val expectedBigInt = BigInteger.fromInt(-123)

        assertTrue { negatedBigInt == expectedBigInt }
    }

    @Test
    fun numberOfDigitsTest() {
        val bigInteger = BigInteger.parseString("123456789012345678901234567890123456", 10)
        val numberOfDigits = bigInteger.numberOfDecimalDigits()
        val expectedDigits = 36L
        assertTrue { numberOfDigits == expectedDigits }
    }

    @Test
    fun numberOfDigitsBigTest() {
        var bigInteger = BigInteger.ONE
        for (i in 1..200L) {
            val numberOfDigits = bigInteger.numberOfDecimalDigits()
            bigInteger = bigInteger * 10
            assertTrue { numberOfDigits == i }
        }
    }

    @Test
    fun testBitAt() {
        assertTrue {
            val bigInt = 2.toBigInteger()
            bigInt.bitAt(0) == false && bigInt.bitAt(1) == true
        }
        assertTrue {
            val bigInt = Long.MAX_VALUE.toBigInteger() + 1
            (bigInt.bitAt(62) == false) && (bigInt.bitAt(63) == true)
        }
        assertTrue {
            val bigInt = ULong.MAX_VALUE.toBigInteger() + 1
            bigInt.bitAt(63) == false && bigInt.bitAt(64) == true
        }

        assertTrue {
            val bigInt = Long.MAX_VALUE.toBigInteger()
            (0..62L).fold(true) { acc, i ->
                acc and bigInt.bitAt(i)
            }
        }
    }

    @Test
    fun setBitAtTest() {
        assertTrue {
            val bigInt = BigInteger.fromInt(4)
            val result = bigInt.setBitAt(1, true)
            result == 6.toBigInteger()
        }

        assertTrue {
            val bigInt = BigInteger.fromInt(6)
            val result = bigInt.setBitAt(1, false)
            result == 4.toBigInteger()
        }

        assertTrue {
            val bigInt = BigInteger.fromLong(Long.MAX_VALUE)
            val result = bigInt.setBitAt(32, false)
            result == (Long.MAX_VALUE.toBigInteger() - 2.toBigInteger().pow(32))
        }

        assertTrue {
            val bigInt = BigInteger.fromLong(Long.MAX_VALUE) - 2.toBigInteger().pow(32)
            val result = bigInt.setBitAt(32, true)
            result == (Long.MAX_VALUE.toBigInteger())
        }
    }

    @Test
    fun bitLengthTestSimple() {
        assertTrue {
            val bigInt = BigInteger.fromInt(0)
            val result = bigInt.bitLength()
            result == 0
        }

        assertTrue {
            val bigInt = BigInteger.fromInt(1)
            val result = bigInt.bitLength()
            result == 1
        }

        assertTrue {
            val bigInt = BigInteger.fromInt(2)
            val result = bigInt.bitLength()
            result == 2
        }

        assertTrue {
            val bigInt = BigInteger.fromInt(4)
            val result = bigInt.bitLength()
            result == 3
        }
    }

    @Test
    fun bitLengthTest() {
        for (i in 0..4096) {
            val bigInt = BigInteger.ONE.shl(i)
            val bitLength = bigInt.bitLength()
            assertEquals(i + 1, bitLength)
        }
    }
}
