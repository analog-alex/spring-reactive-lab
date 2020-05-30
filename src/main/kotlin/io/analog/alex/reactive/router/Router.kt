package io.analog.alex.reactive.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import java.time.Instant

@Configuration
class Router {

    @Bean
    fun route() = router {
        GET("/ping") {
            ServerResponse.ok().body(fromValue("Server is up at ${Instant.now()}"))
        }
    }
}