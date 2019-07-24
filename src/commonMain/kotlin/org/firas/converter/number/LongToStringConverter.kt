package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class LongToStringConverter: Converter<Long?, String?> {

    override fun convert(src: Long?): String? {
        return src?.toString()
    }
}