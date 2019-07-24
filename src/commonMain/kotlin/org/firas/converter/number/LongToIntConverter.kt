package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class LongToIntConverter: Converter<Long?, Int?> {

    override fun convert(src: Long?): Int? {
        return src?.toInt()
    }
}