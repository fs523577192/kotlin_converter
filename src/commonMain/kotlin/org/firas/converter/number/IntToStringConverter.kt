package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class IntToStringConverter: Converter<Int?, String?> {

    override fun convert(src: Int?): String? {
        return src?.toString()
    }
}