package com.examples.feature

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan("com.examples.feature")
open class SpringBootFeatureApplication

fun main(args: Array<String>) {
    runApplication<SpringBootFeatureApplication>(*args)
}
