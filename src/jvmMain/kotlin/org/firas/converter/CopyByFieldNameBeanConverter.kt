package org.firas.converter

import org.firas.converter.number.*
import org.firas.java_bean_utils.BeanFieldGetter
import org.firas.java_bean_utils.BeanFieldSetter
import org.firas.java_bean_utils.getObjectType
import org.firas.math.BigDecimal
import org.firas.math.BigInteger
import java.lang.reflect.Constructor
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import kotlin.reflect.KClass

/**
 * Convert a bean of type SRC to a bean of type DEST.
 *
 * The type DEST must have a public constructor that has no argument.
 *
 * The the non-static and non-final field of the output bean will be
 * assigned (copied) from the non-static field of the same name in the
 * input bean, if the non-static field of the same name exists in the
 * input bean and the field in the output bean is assignable from the
 * field in the input bean.
 *
 * This converter copies field by using `BeanFieldGetter` and
 * `BeanFieldSetter`
 *
 * @author Wu Yuping
 */
class CopyByFieldNameBeanConverter<SRC, DEST>(
        private val srcClass: Class<SRC>,
        private val destClass: Class<DEST>,
        private val copyStaticField: Boolean,
        private val tryConvertType: Boolean,
        private val copyFieldsWithoutPublicAccessor: Boolean
): Converter<SRC, DEST> {

    companion object {

        @JvmStatic
        private val converterMapping: Map<KClass<*>, Map<KClass<*>, Converter<*, *>>>

        init {
            val temp = HashMap<KClass<*>, Map<KClass<*>, Converter<*, *>>>()

            val fromBoolean = HashMap<KClass<*>, Converter<*, *>>()
            fromBoolean[String::class] = BooleanToStringConverter()

            val fromInteger = HashMap<KClass<*>, Converter<*, *>>()
            fromInteger[String::class] = IntToStringConverter()
            fromInteger[Long::class] = IntToLongConverter()
            fromInteger[Double::class] = IntToDoubleConverter()
            fromInteger[BigInteger::class] = IntToBigIntegerConverter()
            temp[Int::class] = fromInteger

            val fromLong = HashMap<KClass<*>, Converter<*, *>>()
            fromLong[String::class] = LongToStringConverter()
            fromLong[Int::class] = LongToIntConverter()
            fromLong[Double::class] = LongToDoubleConverter()
            fromLong[BigInteger::class] = LongToBigIntegerConverter()
            fromLong[BigDecimal::class] = LongToBigDecimalConverter()
            temp[Long::class] = fromLong

            val fromDouble = HashMap<KClass<*>, Converter<*, *>>()
            fromDouble[String::class] = DoubleToStringConverter()
            fromDouble[Int::class] = DoubleToIntConverter()
            fromDouble[Long::class] = DoubleToLongConverter()
            temp[Double::class] = fromDouble

            val fromBigInteger = HashMap<KClass<*>, Converter<*, *>>()
            fromBigInteger[String::class] = BigIntegerToStringConverter()
            temp[BigInteger::class] = fromBigInteger

            val fromBigDecimal = HashMap<KClass<*>, Converter<*, *>>()
            fromBigDecimal[String::class] = BigDecimalToStringConverter()
            temp[BigDecimal::class] = fromBigDecimal

            val fromString = HashMap<KClass<*>, Converter<*, *>>()
            fromString[Boolean::class] = StringToBooleanConverter()
            fromString[Int::class] = StringToIntConverter()
            fromString[Long::class] = StringToLongConverter()
            fromString[Double::class] = StringToDoubleConverter()
            fromString[BigInteger::class] = StringToBigIntegerConverter()
            fromString[BigDecimal::class] = StringToBigDecimalConverter()
            temp[String::class] = fromString

            this.converterMapping = temp
        }
    }

    constructor(srcClass: Class<SRC>, destClass: Class<DEST>, copyStaticField: Boolean, tryConvertType: Boolean):
            this(srcClass, destClass, copyStaticField, tryConvertType, true)

    constructor(srcClass: Class<SRC>, destClass: Class<DEST>, copyStaticField: Boolean):
            this(srcClass, destClass, copyStaticField, true)

    constructor(srcClass: Class<SRC>, destClass: Class<DEST>): this(srcClass, destClass, false)

    private val destProvider: () -> DEST
    private val mapping: Map<BeanFieldGetter<SRC>, Pair<BeanFieldSetter<DEST>, Converter<*, *>?>>

    init {
        this.destProvider = try {
            val destConstructor = this.destClass.getConstructor()
            ({ destConstructor.newInstance() })
        } catch (ex: NoSuchMethodException) {
            { destClass.newInstance() }
        }

        val temp = HashMap<BeanFieldGetter<SRC>, Pair<BeanFieldSetter<DEST>, Converter<*, *>?>>()

        var clazz: Class<*>? = this.srcClass
        while (null != clazz && Object::class.java != clazz) {
            clazz.declaredFields.forEach {
                if (copyStaticField || !Modifier.isStatic(it.modifiers)) {
                    val relatedField = getCopyDestination(it)
                    if (null != relatedField) {
                        val getter = BeanFieldGetter(srcClass, it)
                        val setter = BeanFieldSetter(destClass, relatedField.first)
                        temp[getter] = Pair(setter, relatedField.second)
                    }
                }
            }
            clazz = clazz.superclass
        }
        this.mapping = temp
    }

    override fun convert(src: SRC): DEST {
        val result = this.destProvider()
        this.mapping.forEach { (getter, setterAndConverter) ->
            val fieldValue = getter.getValue(src)

            val setter = setterAndConverter.first
            val converter = setterAndConverter.second as Converter<Any?, Any?>?
            setter.setValue(result, if (null != converter) converter.convert(fieldValue) else fieldValue)
        }
        return result
    }

    private fun getCopyDestination(field: Field): Pair<Field, Converter<*, *>?>? {
        val fieldType = getObjectType(field.type)
        var clazz: Class<*>? = this.destClass
        while (null != clazz && Object::class.java != clazz) {
            try {
                val tempField = clazz.getDeclaredField(field.name)

                if (Modifier.isFinal(tempField.modifiers)) {
                    return null
                }
                if (!this.copyStaticField && Modifier.isStatic(tempField.modifiers)) {
                    return null
                }

                val tempType = getObjectType(tempField.type)
                if (tempType.isAssignableFrom(fieldType)) {
                    return Pair(tempField, null)
                }

                if (this.tryConvertType) {
                    val converter = converterMapping[fieldType.kotlin]?.get(tempType.kotlin)
                    if (null != converter) {
                        return Pair(tempField, converter)
                    }
                }
            } catch (ex: NoSuchFieldException) { }
            clazz = clazz.superclass
        }
        return null
    }
}