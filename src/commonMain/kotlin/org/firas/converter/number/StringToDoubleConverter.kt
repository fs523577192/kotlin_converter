package org.firas.converter.number

import org.firas.converter.Converter

/**
 *
 * @author Wu Yuping
 */
class StringToDoubleConverter: Converter<String, Double> {

    override fun convert(src: String): Double {
        return src.toDouble()
    }
}