## Descriptive changelog
(All dates are DD.MM.YYYY)

##### 0.3.10-SNAPSHOT - current snapshot
- Fix for #280, return byte array containing zero instead of empty array when big integer is zero.

##### 0.3.9 - 28.1.2024
- Bump to kotlin 1.9.21
- Expanded BigInteger API to expose bitLength as proposed by #254
- Added wasm-js target
- Fix for #269, thanks to @CodeServant
- Fix for #276, BigDecimal divrem was not rounding properly
- Fix for #277, round half to even was wrong when exponent was 0

##### 0.3.8 - 11.2.2023
- Bump to Kotlin 1.8.10
- Fix for #253, empty string is not a valid floating point number any more and parsing it throws ArithmeticException
- Fix for #245, division test failures run in coroutines were ignored.
- 
##### 0.3.7 - 6.8.2022
- Bump to Kotlin 1.7.10
- Fix for #239, toPlainString which was supposed to return same result as JVM was truncating zeroes when scale was used. 
- Fix for #238, wrong exponent in resolved decimal precision was used in divideAndRemainder
- Fix for #237, when precision and exponent are same invalid value was returned
- Fix for #231, exception incorrectly thrown when using scale (the library was only checking for unlimited precision instead of that and presence of scale)

##### 0.3.6 - 22.5.2022
- Provide big integer/big decimal to their java counterpart conversion methods. They are slow as they rely on string conversion. #230
- Update to Kotlin 1.6.21
- Fix for #229, incorrect toDouble result when exact result is not required and significand was larger than Long.MAX_VALUE


##### 0.3.4-SNAPSHOT - 15.1.2022
- Throw a specific exception when exponentiation of zero with negative exponent is attempted (#206)
- Remove zero counting debug log (#210)
- Fix for invalid decimal precision when dividend has exponent -1 (#195)
- **API CHANGE** Narrowing function (longValue, intValue, doubleValue...) are now defaulting to `exactRequired` which means they
will throw ArithmeticException if the conversion cannot be done without loss.
- Use temporary javascript comparison workaround to handle precision loss. 
  

##### 0.3.3 - 9.11.2021
- Add support for apple silicon (#188 #194)

##### 0.3.2 - 5.9.2021
- Added kotlinx serialization support library
- Enabled gradle dependencies verification (bootstrapped)
- Fix for losing decimal mode when using unary minus (#184)
- Fix for losing sign when narrowing to long from big integer (#186)


##### 0.3.1 - 10.5.2021
- Fix for #176, a case of unclear API. Methods `roundToDigitPositionAfterDecimalPoint` and `roundToDigitPosition` would set decimal precision to the number of digits present in the result after the rounding was completed. Now they only set decimal precision if it's explicitly set, otherwise it stays unlimited.
- Bump to 1.5.0


##### 0.3.0 - 17.4.2021
- Fixed losing scale when there is a carry in addition in BigDecimal.
- Fixed BigInteger `numberOfDecimalDigits` which would return 0 for 0 instead of 1.
- Fixed #168 - Fix invalid rounding results
Development roadmap for version 0.3.0 states 'API statbilization' so:
- **API CHANGE** Extensions functions used to create BigDecimal from primitive types have been split into 'toBigDecimal(exponentModifier...)'
  and 'toBigDecimalUsingSignificandAndExponent(exponent...)' to bring more clarity to the API and solve #164
- **API CHANGE** BigInteger uses a sum of magnitude elements as part of hash code instead of array hash code as it wasn't stable on JS
- **API CHANGE** BigDecimal hashCode doesn't include decimal mode anymore, so it respects contract that hash code equals another hash code
  when equals returns true.
- **API CHANGE** BigDecimal equals doesn't consider decimal mode anymore. Two big decimals are now equal only if they represent the same number.
- Fixed decimal mode precision/scale mismatch in BigDecimal #161
  - **NOTE: API CHANGE** final precision is now desiredPrecision + scale
- Add @SharedImmutable to BigInteger to support native multithreading #159
##### 0.2.8 - 15.2.2021

- Fixed support for watchosX64/watchos86 (#150)
- Fixed parsing characters outside of radix range (#152)
- Fixed invalid byte array intialization (#153)
- Fixed rounding KDoc (#156)

##### 0.2.7 - 6.2.2021
Same as 0.2.4 just a successful upload and release to Maven central

##### 0.2.5 - 0.2.6 - 5.2.2021
Failed uploads to maven central, don't use these versions.
https://status.maven.org/incidents/z70skgbq8vgc

##### 0.2.4 - 5.2.2021
- Bump to kotlin 1.4.30
- Fix invalid to string when big decimal is zero (#148)
- Fix xor test which was previously testing bit shifting instead of xor (#147)
- Fix biginteger xor operands magnitude array length mismatch (#144)
- Fix biginteger bitwise operations sign (#142)
- Added (back) support for legacy js target (#138)
##### 0.2.3 - 28.11.2020
- Bump to Kotlin 1.4.20
- Fix for invalid exponent rounding and string representation (#139)
- Returned mingwx86 target (#137)
- Fixed #134 
- Fixed #130 Conversion to double off by power of 10
- Fixed #132 BigDecimal narrow functions toFloat() and toDouble, with exactRequired = false sometimes wrongly fail
- Fixed floatValue and doubleValue narrowing functions (pull request #135)
- Fixed invalid string parsing of big decimals 

##### 0.2.2 - 10.10.2020 - Rework infinite precision division
- Issue #127 - invalid division when using unlimited precision wasn't completely handled 
in the previous release. With this release that division case was completely reworked and additional tests were added.

##### 0.2.1 - 8.10.2020 - Fix infinite precision division, kotlin version bump
- Fixed #127 BigDecimal divide not working properly
- Added tests to cover case from #127
- Bumped kotlin to 1.4.10


##### 0.2.0 - 18.8.2020 - Improvements, optimizations, bugfixes and Kotlin 1.4.0
- Improvement #122 Add "scale" support to BigDecimal
- Fixed #118 Rounding issue on division
- Improvement #116 Introduce local gradle varibale to enable JVM target on different platforms
- Fixed #112 BigDecimal divideAndRemainder bad result
- Fixed #111 - BigDecimal narrowing
- Fixed #104 - BigInteger.toByteArray should prefer ByteArray over Array<Byte>
- Fixed #103 - Removed coroutines
- Fixed #90 - Incorrect Result From Negative Double
- Fixed #88 - BigInteger.bitAt does not work correctly
- Fixed #86 - Rounding fails when integer part is 9
- Fixed #88 - BigInteger.bitAt() returns invalid value
- Built with Kotlin 1.4-M3
- Reworked to(U)ByteArray conversion methods
    - from and to conversions were not consistent, from(U)ByteArray expected a string of bytes ordered in little or big endian,
    while to(U)ByteArray produced `Int` or `Long` represented as byte with little endian or big endian order.
    - Replaced with a consistent to and from byte/ubyte array conversions
        - `fromUByteArray` always expects a big-endian ordered array of unsigned bytes
        - `fromByteArray` always expects a big-endian ordered array of bytes
        - `toUByteArray` produces unsigned byte array in big-endian order 
        - `toByteArray` produces signed byte array in big-endian order
    - There are two helper methods that convert  to and from a two's complement ByteArray, this form conforms to Java BigIntegers toByteArray
        - `fromTwosComplementByteArray` expects a two's complement ByteArray with at least one sign bit
        - `toTwosComplementByteArray`produces a two's complement ByteArray with at least one sign bit
- Added `secureOverwrite` to BigNumber interface, with role of overwriting backing structures with zeroes. It's meant to 
be used by libraries that require such a functionlity (i.e. crypto). The function also breaks immutability contract
of BigNumber implementations, and further operations with that instances have undefined results.

##### 0.1.5 - 07.01.2020 - Adding linux arm targets, BigDecimal bug fixes
- Version bump to kotlin 1.3.61
- Gradlew wrapper bump to 6.0.1
- Added Linux Arm 64, and Linux Arm 32 HFP build targets
- Fixed smaller issues 


##### 0.1.4 - 10.12.2019 - BigDecimal improvements, MINGW64 and MINGW86 targets added
- Main library now has dependancies only on the kotlin standard library (for now, coroutines will be coming back at some point in the future).
- Renamed BigDecimal `round` method to `roundSignificand`, as it describes what it does more precisely
- Added `roundAtDigitPosition` and `roundAfterDecimalPoint` convenience methods.
- Use Long instead of BigInteger for BigDecimal exponent.
- Adding MingwX64 and MingwX86 targets.

##### 0.1.3 - 19.11.2019 - Kotlin and Gradle version bump 
- Kotlin version bump to 1.3.60 - especially important as there was a comparison performance improvement for inline classes 
which are heavily used by bignum library (ULong/UInt).
- Actually bumped gradle to 5.6.1, it was mistakenly left at 5.1.1 in library versions 0.1.1 and 0.1.2
- Cleaned up dependancies, coroutines are currently used only in test modules so they are moved there.


##### 0.1.2 - 17.11.2019 - Performance improvements
- Removed removeLeadingZeros and replaced with countLeadingZeros and appropriate algorithm changes.
- Realized plural of zero is zeros not zeroes.
- Improved exponentiation to use square-and-multiply method.



##### 0.1.1 - 19.10.2019 - Multiplication algorithm improvements
- Implemented Toom-Cook-3 multiplication, although still slow because of inefficient division.
- Bumped gradle version to 5.6.1, which means that the published Gradle Metadata will be 1.0, making
metadata resolution available only on Gradle >= 5.3
- Fixed several issues related to big decimal comparison, modular integer sign, etc.
- Added more BigDecimal extension functions.
- Added ModularBigInteger extension functions.
- Added Karatsuba multiplication.
- Added `copy` and `moveDecimalPoint` methods.
- Added `fromUByteArray` and `toUByteArray`.


##### 0.1.0 - 31.7.2019 ByteArray support and other improvements
- Added toByteArray and fromByteArray.
- Added toFloat and toDouble to BigInteger and ModularBigInteger classes.
- Added BigInteger creation from Float and Double by using `tryFromFloat` and `tryFromDouble`, with optional exact 
parameter to preserve precision.
- Added BigInteger comparison with Float and Double.
- Added BigDecimal configuration option to switch to expanded representation instead of scientific when calling `toString()`.
- Improved ModularBigInteger exponentiation algorithm, based on Bruce Schneier Applied Cryptography pesudocode.

##### 0.0.9 - 11.5.2019 Adding modular integer support, changing api
- Added modular integers - ModularBigInteger.
- Added modInverse method to BigInteger.
- Extracted interfaces that model big numbers better (BigNumber<BigType> interface and accompanying interfaces)
- Implemented integer reciprocal based on newton iteration (Based on paper by Yiping Cheng, Ze Lie : Refinement of a newton reciprocal algorithm for arbitrary precision numbers)
- Implemented division by reciprocal multiplication. Not used by division at the moment as it is unoptimized and slower than basecase division in early benchmarks.
- Fixed a bug in Int32 shift right when shift amount was an exact multiple of word size 
- Added constructor overloads
- Added value methods (intValue, longValue...)
- Renamed invPrecise() bigInteger method to not()
- Renamed numberOfDigits() to numberOfDecimalDigits()
- Introduced BigNumber and BitwiseOperations interfaces 
- Added iOS ARM 32bit support 


##### 0.0.8 - 02.04.2019 - Minor update 
- Released ios (for X64 and arm) and macos X64 artifacts
- No functional changes


##### 0.0.7 - 31.3.2019 Minor update
- Added extension functions for `String` to `BigInteger` and `BigDecimal`
- Fixed a couple of parsing issues
- Added this changelog

##### 0.0.6 - 30.3.2019 - Adding floating point support/BigDecimal  
- Added BigDecimal
- Fixed several BigInteger bugs

#### 0.0.5 - 20.3.2019 - Initial release/BigInteger 
- Added Big integer support

