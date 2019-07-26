package org.firas.converter.number

import org.firas.converter.Converter
import kotlin.jvm.JvmOverloads

/**
 *
 * @author Wu Yuping
 */
class StringToLongConverter @JvmOverloads constructor(val radix: Int = 10): Converter<String?, Long?> {

    override fun convert(src: String?): Long? {
        return src?.toLong(this.radix)
    }
}