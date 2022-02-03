package io.icure.md.client

import com.github.benmanes.caffeine.cache.Cache
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.*

suspend fun <K, V> Cache<K, Deferred<Optional<V>>>.defGet(key: K, loader: suspend (K) -> V?): V? =
    this.get(key) { GlobalScope.async { Optional.ofNullable(loader(key)) } }.await().orElse(null)

suspend fun <K, V> Cache<K, Deferred<Optional<V>>>.defPut(key: K, loader: suspend (K) -> V?) =
    this.put(key, GlobalScope.async { Optional.ofNullable(loader(key)) })

fun <T> T.applyIf(predicate: (T) -> Boolean, action: (T) -> T): T {
    return if (predicate.invoke(this)) action.invoke(this) else this
}
