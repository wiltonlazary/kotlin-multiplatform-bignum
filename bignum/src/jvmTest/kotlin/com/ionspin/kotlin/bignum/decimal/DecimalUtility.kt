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

import com.ionspin.kotlin.bignum.integer.base63.toJavaBigInteger
import kotlin.math.absoluteValue

/**
 * Created by Ugljesa Jovanovic
 * ugljesa.jovanovic@ionspin.com
 * on 24-Mar-2019
 */

fun BigDecimal.toJavaBigDecimal(): java.math.BigDecimal {
    if (this.precision > Int.MAX_VALUE) {
        throw RuntimeException("Numbers with more digits than Int.MAX_VALUE cannot be converted to java BigDecimal")
    }
    if (this == BigDecimal.ZERO) {
        return java.math.BigDecimal.ZERO
    }
    return if (exponent > 0) {
        java.math.BigDecimal(
            this.significand.toJavaBigInteger(),
            (this.precision - this.exponent - 1).toInt()
        )
    } else {
        java.math.BigDecimal(
            this.significand.toJavaBigInteger(),
            (this.precision + this.exponent.absoluteValue - 1).toInt()
        )
    }
//    return java.math.BigDecimal(this.toStringExpanded())
}
