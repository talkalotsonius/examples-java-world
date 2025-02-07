package com.examples.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SpringBootBoilerplateApplication

fun main(args: Array<String>) {
    runApplication<SpringBootBoilerplateApplication>(*args)
}
