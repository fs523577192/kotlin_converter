package org.firas.converter.number

import org.firas.converter.Converter
import org.firas.math.BigInteger

/**
 *
 * @author Wu Yuping
 */
class StringToBigIntegerConverter(val radix: Int): Converter<String, BigInteger> {

    override fun convert(src: String): BigInteger {
        return BigInteger.Companion.valueOf(src, this.radix)
    }
}