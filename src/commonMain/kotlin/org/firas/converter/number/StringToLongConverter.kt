package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class StringToLongConverter(val radix: Int): Converter<String?, Long?> {

    constructor(): this(10)

    override fun convert(src: String?): Long? {
        return src?.toLong(this.radix)
    }
}