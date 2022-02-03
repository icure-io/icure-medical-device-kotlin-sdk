package io.icure.kraken.client.infrastructure

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer

class ByteArrayWrapperDeserializer : StdScalarDeserializer<ByteArrayWrapper>(ByteArrayWrapper::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = ByteArrayWrapper(p.binaryValue)
}

class ByteArrayWrapperSerializer : StdScalarSerializer<ByteArrayWrapper>(ByteArrayWrapper::class.java) {
    override fun serialize(value: ByteArrayWrapper?, gen: JsonGenerator, provider: SerializerProvider) = value?.let {
        gen.writeBinary(it.byteArray, 0, it.size)
    } ?: gen.writeNull()
}
