package com.tw.shoppingcart.config

import org.springframework.boot.actuate.trace.http.HttpTraceRepository
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringActuatorConfiguration {

    @Bean
    fun httpTraceRepository(): HttpTraceRepository? {
        // This is just for development environment,  For production environments, a tracing or
        // observability solution, such as Zipkin or Spring Cloud Sleuth is recommended.
        // Alternatively, you can create your own HttpTraceRepository!
        return InMemoryHttpTraceRepository()
    }
}
