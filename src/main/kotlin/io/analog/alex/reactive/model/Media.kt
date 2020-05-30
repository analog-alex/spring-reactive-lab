package io.analog.alex.reactive.model

import org.jetbrains.annotations.NotNull
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import java.time.LocalDate

enum class Type { MOVIE, GAME, BOOK, ALBUM, PAITING, SCULPTURE}

@Document
data class Media (
    @MongoId
    val id: String? = null,

    @NotNull
    val type: Type,

    @NotNull
    val name: String,

    @NotNull
    val author: String,

    @NotNull
    val createdAt: LocalDate,

    @NotNull
    val rating: Integer
)