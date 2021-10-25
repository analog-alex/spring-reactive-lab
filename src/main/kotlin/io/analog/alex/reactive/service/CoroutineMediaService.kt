package io.analog.alex.reactive.service

import io.analog.alex.reactive.model.Media
import io.analog.alex.reactive.model.Type
import io.analog.alex.reactive.repository.ReactiveMediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service

@Service
class CoroutineMediaService(
    private val reactiveMediaRepository: ReactiveMediaRepository
) {

    suspend fun findAll(): Flow<Media> {
        return reactiveMediaRepository.findAll().asFlow()
    }

    suspend fun findByAuthor(author: String): Flow<Media> {
        return reactiveMediaRepository.findAllByAuthor(author).asFlow()
    }

    suspend fun findByType(type: Type): Flow<Media> {
        return reactiveMediaRepository.findAllByType(type).asFlow()
    }

    suspend fun create(entity: Media): Media? {
        val found = reactiveMediaRepository.findAllByName(entity.name).asFlow().toList()

        return if (found.isEmpty()) {
            reactiveMediaRepository.insert(entity).awaitSingle()
        } else {
            null
        }
    }
}
