package org.firas.converter.number

import org.firas.converter.Converter
import kotlin.jvm.JvmOverloads

/**
 *
 * @author Wu Yuping
 */
class StringToIntConverter @JvmOverloads constructor(val radix: Int = 10): Converter<String?, Int?> {

    override fun convert(src: String?): Int? {
        return src?.toInt(this.radix)
    }
}