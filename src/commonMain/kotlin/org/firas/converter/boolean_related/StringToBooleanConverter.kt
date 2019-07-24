package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class StringToBooleanConverter: Converter<String?, Boolean?> {

    override fun convert(src: String?): Boolean? {
        return if (null == src) null else "true" == src
    }
}