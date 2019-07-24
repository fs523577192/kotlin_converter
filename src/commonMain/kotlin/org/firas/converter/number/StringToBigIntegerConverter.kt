package org.firas.converter.number

import org.firas.converter.Converter
import org.firas.math.BigInteger

/**
 *
 * @author Wu Yuping
 */
class StringToBigIntegerConverter(val radix: Int): Converter<String?, BigInteger?> {

    constructor(): this(10)

    override fun convert(src: String?): BigInteger? {
        return if (null == src) null else BigInteger.Companion.valueOf(src, this.radix)
    }
}