package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class IntToLongConverter: Converter<Int?, Long?> {

    override fun convert(src: Int?): Long? {
        return src?.toLong()
    }
}