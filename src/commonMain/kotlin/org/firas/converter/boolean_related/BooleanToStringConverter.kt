package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class BooleanToStringConverter: Converter<Boolean?, String?> {

    override fun convert(src: Boolean?): String? {
        return src?.toString()
    }
}