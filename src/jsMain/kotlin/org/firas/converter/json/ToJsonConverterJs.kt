package org.firas.converter.json

/**
 *
 * @author Wu Yuping
 */
class ToJsonConverterJs<T>: ToJsonConverter<T> {

    override fun convert(src: T): String {
        return JSON.stringify(src)
    }
}