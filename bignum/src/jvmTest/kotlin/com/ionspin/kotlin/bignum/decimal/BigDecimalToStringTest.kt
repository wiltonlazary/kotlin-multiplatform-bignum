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

package com.ionspin.kotlin.bignum.decimal

import org.junit.Test
import kotlin.test.assertTrue

/**
 * Created by Ugljesa Jovanovic
 * ugljesa.jovanovic@ionspin.com
 * on 17-Dec-2019
 */
@ExperimentalUnsignedTypes
class BigDecimalToStringTest {

    @Test
    fun testPositiveToString() {

        assertTrue {
            val negativeExpanded = (0.6).toBigDecimal().toStringExpanded()
            negativeExpanded == "0.6"
        }

        assertTrue {
            val negativeExpanded = (6.0).toBigDecimal().toStringExpanded()
            negativeExpanded == "6"
        }

        assertTrue {
            val negativeExpanded = (6.1).toBigDecimal().toStringExpanded()
            negativeExpanded == "6.1"
        }

        assertTrue {
            val negativeExpanded = (60.0).toBigDecimal().toStringExpanded()
            negativeExpanded == "60"
        }

        assertTrue {
            val negativeExpanded = (60.1).toBigDecimal().toStringExpanded()
            negativeExpanded == "60.1"
        }

        assertTrue {
            val negativeExpanded = (660.661).toBigDecimal().toStringExpanded()
            negativeExpanded == "660.661"
        }
    }

    @Test
    fun testNegativeToString() {

        assertTrue {
            val negativeExpanded = (-0.6).toBigDecimal().toStringExpanded()
            negativeExpanded == "-0.6"
        }

        assertTrue {
            val negativeExpanded = (-6.0).toBigDecimal().toStringExpanded()
            negativeExpanded == "-6"
        }

        assertTrue {
            val negativeExpanded = (-6.1).toBigDecimal().toStringExpanded()
            negativeExpanded == "-6.1"
        }

        assertTrue {
            val negativeExpanded = (-60.0).toBigDecimal().toStringExpanded()
            negativeExpanded == "-60"
        }

        assertTrue {
            val negativeExpanded = (-60.1).toBigDecimal().toStringExpanded()
            negativeExpanded == "-60.1"
        }
        assertTrue {
            val negativeExpanded = (-660.661).toBigDecimal().toStringExpanded()
            negativeExpanded == "-660.661"
        }
    }
}