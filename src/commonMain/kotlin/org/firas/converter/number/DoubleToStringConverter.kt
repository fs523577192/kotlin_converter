package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class DoubleToStringConverter: Converter<Double?, String?> {

    override fun convert(src: Double?): String? {
        return src?.toString()
    }
}