package org.firas.converter.number

import org.firas.converter.Converter
import org.firas.math.BigDecimal

/**
 *
 * @author Wu Yuping
 */
class StringToBigDecimalConverter: Converter<String, BigDecimal> {

    override fun convert(src: String): BigDecimal {
        return BigDecimal.valueOf(src)
    }
}