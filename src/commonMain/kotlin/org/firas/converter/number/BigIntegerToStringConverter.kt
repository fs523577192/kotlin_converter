package org.firas.converter.number

import org.firas.converter.Converter
import org.firas.math.BigInteger

/**
 *
 * @author Wu Yuping
 */
class BigIntegerToStringConverter: Converter<BigInteger?, String?> {

    override fun convert(src: BigInteger?): String? {
        return src?.toString()
    }
}