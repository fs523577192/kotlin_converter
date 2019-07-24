package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class IntToDoubleConverter: Converter<Int?, Double?> {

    override fun convert(src: Int?): Double? {
        return src?.toDouble()
    }
}