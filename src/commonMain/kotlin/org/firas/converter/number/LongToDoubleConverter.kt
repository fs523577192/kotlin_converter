package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class LongToDoubleConverter: Converter<Long?, Double?> {

    override fun convert(src: Long?): Double? {
        return src?.toDouble()
    }
}