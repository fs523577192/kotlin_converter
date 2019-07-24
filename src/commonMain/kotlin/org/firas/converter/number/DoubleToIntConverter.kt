package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class DoubleToIntConverter: Converter<Double?, Int?> {

    override fun convert(src: Double?): Int? {
        return src?.toInt()
    }
}