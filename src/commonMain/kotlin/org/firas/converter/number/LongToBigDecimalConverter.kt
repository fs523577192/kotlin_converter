package org.firas.converter.number

import org.firas.converter.Converter
import org.firas.math.BigDecimal

/**
 *
 * @author Wu Yuping
 */
class LongToBigDecimalConverter: Converter<Long?, BigDecimal?> {

    override fun convert(src: Long?): BigDecimal? {
        return if (null == src) null else BigDecimal.Companion.valueOf(src)
    }
}