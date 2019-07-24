package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class DoubleToLongConverter: Converter<Double?, Long?> {

    override fun convert(src: Double?): Long? {
        return src?.toLong()
    }
}