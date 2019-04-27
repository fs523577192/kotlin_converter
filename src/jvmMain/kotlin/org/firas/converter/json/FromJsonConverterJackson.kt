package org.firas.converter.json

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 *
 * @author Wu Yuping
 */
class FromJsonConverterJackson<T>: FromJsonConverter<T> {

    companion object {
        val threadLocal = object : ThreadLocal<ObjectMapper>() {
            protected override fun initialValue(): ObjectMapper {
                return jacksonObjectMapper()
            }
        }
    }

    override fun convert(src: String): T {
        return threadLocal.get().readValue(src, object: TypeReference<T>() {})
    }
}
