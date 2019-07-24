package org.firas.converter.number

import org.firas.converter.Converter
import org.firas.math.BigInteger

/**
 *
 * @author Wu Yuping
 */
class IntToBigIntegerConverter: Converter<Int?, BigInteger?> {

    override fun convert(src: Int?): BigInteger? {
        return if (null == src) null else BigInteger.Companion.valueOf(src.toLong())
    }
}