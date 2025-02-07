package com.examples.ktnullexamples

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class NullSafetyApplication

fun main(args: Array<String>) {
	runApplication<NullSafetyApplication>(*args)
}
