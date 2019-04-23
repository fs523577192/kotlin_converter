package org.firas.converter.json

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/**
 *
 * @author Wu Yuping
 */
class FromJsonConverterJackson<T>: FromJsonConverter<T> {

    override fun convert(src: String): T {
        jacksonObjectMapper().readValue(src, object: TypeReference<T>() {})
    }
}