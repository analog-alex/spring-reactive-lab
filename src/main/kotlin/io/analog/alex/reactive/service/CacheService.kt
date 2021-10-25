package io.analog.alex.reactive.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.redisson.api.RedissonReactiveClient
import org.springframework.stereotype.Service

@Service
class CacheService(
    private val redisson: RedissonReactiveClient
) {

    suspend fun <T> readThroughFor(cacheName: String, key: String, actionIfNotPresent: suspend () -> Flow<T>): Flow<T> {
        val map = redisson.getMap<String, List<T>>(cacheName)
        return try {
            map.get(key).awaitSingle().asFlow()
        } catch (nse: NoSuchElementException) {
            actionIfNotPresent().also { map.put(key, it.toList()).subscribe() }
        }
    }

    suspend fun <T> writeAndEvict(cacheName: String, writeAction: suspend () -> T?): T? {
        return writeAction()?.also { evictCache<T>(cacheName) }
    }

    private suspend fun <T> evictCache(cacheName: String) {
        val map = redisson.getMap<String, List<T>>(cacheName)
        map.readAllKeySet().awaitSingle().asSequence()
            .map {
                map.remove(it)
            }
            .forEach {
                it.awaitSingle()
            }
    }
}