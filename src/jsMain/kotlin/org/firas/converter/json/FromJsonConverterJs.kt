package org.firas.converter.json

/**
 * @author Wu Yuping
 */
class FromJsonConverterJs<T>: FromJsonConverter<T> {

    override fun convert(src: String): T {
        return JSON.parse(src)
    }
}
