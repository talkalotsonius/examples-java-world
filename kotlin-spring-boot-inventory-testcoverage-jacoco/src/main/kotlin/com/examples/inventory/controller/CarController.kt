package com.examples.inventory.controller

import com.examples.inventory.controller.model.CarDto
import com.examples.inventory.service.CarService

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CarController(private val carService: CarService) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/cars")
    fun getAllCars(): List<CarDto> {
        val carList =  carService.getAllCars().map {
            CarDto(
                id = it.id,
                make = it.make,
                model = it.model,
                finalPrice = it.finalPrice,
                condition = it.condition,
                oldPrice = it.oldPrice,
                date = it.date
            )
        }
        logger.info("List of cars: $carList")
        return carList
    }
}