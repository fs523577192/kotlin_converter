package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class StringToIntConverter(val radix: Int): Converter<String?, Int?> {

    constructor(): this(10)

    override fun convert(src: String?): Int? {
        return src?.toInt(this.radix)
    }
}