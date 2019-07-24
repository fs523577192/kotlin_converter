package org.firas.converter.number

import org.firas.converter.Converter
import org.firas.math.BigInteger

/**
 *
 * @author Wu Yuping
 */
class LongToBigIntegerConverter: Converter<Long?, BigInteger?> {

    override fun convert(src: Long?): BigInteger? {
        return if (null == src) null else BigInteger.Companion.valueOf(src)
    }
}