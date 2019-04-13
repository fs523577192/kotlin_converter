package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class StringToLongConverter(val radix: Int = 10): Converter<String, Long> {

    override fun convert(src: String): Long {
        return src.toLong(this.radix)
    }
}