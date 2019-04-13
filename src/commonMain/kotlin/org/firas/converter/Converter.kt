package org.firas.converter

/**
 *
 * @author Wu Yuping
 */
interface Converter<in SRC, out DEST> {
    fun convert(src: SRC): DEST
}