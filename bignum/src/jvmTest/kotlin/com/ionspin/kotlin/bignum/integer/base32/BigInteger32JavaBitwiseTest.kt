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

package com.ionspin.kotlin.bignum.integer.base32

import kotlin.random.Random
import kotlin.random.nextUInt
import kotlin.test.assertTrue
import kotlin.test.fail
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

/**
 * Created by Ugljesa Jovanovic
 * ugljesa.jovanovic@ionspin.com
 * on 09-Mar-2019
 */

class BigInteger32JavaBitwiseTest {

    @Test
    fun `Test shift left`() {
        val seed = 1
        val random = Random(seed)

        val jobList: MutableList<Job> = mutableListOf()
        for (i in 1..30_000 step 5000) {
            val a = UIntArray(i) { random.nextUInt() }
            val b = random.nextInt(i)
            jobList.add(
                GlobalScope.launch {
                    shiftLeftSingleTest(b, a)
                }
            )
        }
        runBlocking {
            jobList.forEach {
                if (it.isCancelled) {
                    fail("Some of the tests failed")
                }
                it.join()
            }
        }
    }

    fun shiftLeftSingleTest(places: Int, number: UIntArray) {
        assertTrue("Failed for $places and elements ${number.contentToString()}") {
            val a = number
            val result = BigInteger32Arithmetic.shiftLeft(a, places)
            val convertedResult = result.toJavaBigInteger()
            val bigIntResult = a.toJavaBigInteger() shl places
            convertedResult == bigIntResult
        }
    }

    @Test
    fun `Test shift right`() {
        val seed = 1
        val random = Random(seed)
        val jobList: MutableList<Job> = mutableListOf()
        for (i in 1..30_000 step 5000) {
            val a = UIntArray(i) { random.nextUInt() }
            val b = random.nextInt(i)
            jobList.add(
                GlobalScope.launch {
                    shiftRightSingleTest(b, a)
                }
            )
        }
        runBlocking {
            jobList.forEach {
                if (it.isCancelled) {
                    fail("Some of the tests failed")
                }
                it.join()
            }
        }
    }

    @Test
    fun `Test shift right with specific values`() {
        val a = intArrayOf(
            752029288,
            -964625924,
            479674580,
            -1697013934,
            -1956440078,
            -1550357085
        ).toUIntArray()
        shiftRightSingleTest(128, number = a)
    }

    fun shiftRightSingleTest(places: Int, number: UIntArray) {
        assertTrue("Failed for $places and elements ${number.contentToString()}") {
            val result = BigInteger32Arithmetic.shiftRight(number, places)
            val convertedResult = result.toJavaBigInteger()
            val bigIntResult = number.toJavaBigInteger() shr places
            convertedResult == bigIntResult
        }
    }
}
