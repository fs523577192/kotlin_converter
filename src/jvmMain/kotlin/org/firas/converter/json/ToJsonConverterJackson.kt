package org.firas.converter.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 * @author Wu Yuping
 */
class ToJsonConverterJackson<T>: ToJsonConverter<T> {

    companion object {
        val threadLocal = object : ThreadLocal<ObjectMapper>() {
            protected override fun initialValue(): ObjectMapper {
                return jacksonObjectMapper()
            }
        }
    }

    override fun convert(src: T): String {
        return threadLocal.get().writeValueAsString(src)
    }
}
