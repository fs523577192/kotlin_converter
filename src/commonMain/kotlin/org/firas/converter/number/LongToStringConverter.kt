package org.firas.converter.number

import org.firas.converter.Converter
import kotlin.jvm.JvmOverloads

/**
 *
 * @author Wu Yuping
 */
class LongToStringConverter @JvmOverloads constructor(val radix: Int = 10): Converter<Long?, String?> {

    override fun convert(src: Long?): String? {
        return src?.toString(this.radix)
    }
}