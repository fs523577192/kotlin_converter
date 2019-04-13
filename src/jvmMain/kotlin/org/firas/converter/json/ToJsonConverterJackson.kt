package org.firas.converter.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * @author Wu Yuping
 */
class ToJsonConverterJackson<T>: ToJsonConverter<T> {

    override fun convert(src: T): String {
        return jacksonObjectMapper().writeValueAsString(src)
    }
}
