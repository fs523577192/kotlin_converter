package org.firas.converter.number

import org.firas.converter.Converter
import org.firas.math.BigDecimal

/**
 *
 * @author Wu Yuping
 */
class BigDecimalToStringConverter: Converter<BigDecimal?, String?> {

    override fun convert(src: BigDecimal?): String? {
        return src?.toString()
    }
}