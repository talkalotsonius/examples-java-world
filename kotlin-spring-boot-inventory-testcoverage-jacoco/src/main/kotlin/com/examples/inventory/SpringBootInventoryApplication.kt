package com.examples.inventory

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class SpringBootInventoryApplication

fun main(args: Array<String>) {
	runApplication<SpringBootInventoryApplication>(*args)
}
