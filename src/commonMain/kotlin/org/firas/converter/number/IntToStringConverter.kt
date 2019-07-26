package org.firas.converter.number

import org.firas.converter.Converter
import kotlin.jvm.JvmOverloads

/**
 *
 * @author Wu Yuping
 */
class IntToStringConverter @JvmOverloads constructor(val radix: Int = 10): Converter<Int?, String?> {

    override fun convert(src: Int?): String? {
        return src?.toString(this.radix)
    }
}