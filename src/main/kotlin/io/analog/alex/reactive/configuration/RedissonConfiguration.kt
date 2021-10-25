package io.analog.alex.reactive.configuration

import org.redisson.Redisson
import org.redisson.api.RedissonReactiveClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissonConfiguration(
    @Value("\${spring.redis.host:localhost}") val host: String,
    @Value("\${spring.redis.port:6379}") val port: String
) {

    @Bean(destroyMethod = "shutdown")
    fun redisson(): RedissonReactiveClient? {
        val config = Config().apply {
            useSingleServer().address = "redis://$host:$port"
        }
        return Redisson.create(config).reactive()
    }
}