package com.examples.inventory.controller.model

import com.examples.inventory.utils.Condition

import java.time.LocalDate

data class CarDto(
    val id: Int,
    val make: String,
    val model: String,
    val finalPrice: Double,
    val condition: Condition?,
    val oldPrice: Double?,
    val date: LocalDate?
)
