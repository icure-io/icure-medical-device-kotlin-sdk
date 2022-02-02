package io.icure.kraken.client.infrastructure

class ByteArrayWrapper(val byteArray: ByteArray): Iterable<Byte> {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ByteArrayWrapper) return false

        if (!byteArray.contentEquals(other.byteArray)) return false

        return true
    }

    val size: Int
        get() = byteArray.size

    operator fun get(index: Int): Byte = byteArray.get(index)
    operator fun set(index: Int, value: Byte) = byteArray.set(index, value)
    override operator fun iterator(): ByteIterator = byteArray.iterator()

    override fun hashCode(): Int {
        return byteArray.contentHashCode()
    }
}
