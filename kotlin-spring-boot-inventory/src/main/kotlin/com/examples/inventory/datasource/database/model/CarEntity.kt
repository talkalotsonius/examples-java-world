package com.examples.inventory.datasource.database.model

import com.examples.inventory.utils.Condition

import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.EnumType
import java.time.LocalDate


@Entity
@Table(name = "car")
data class CarEntity(
    @Id
    val id: Int,
    val make: String,
    val model: String,
    val price: Double,
    @Enumerated(EnumType.STRING)
    val condition: Condition?,
    val discountPrice: String?,
    val date: LocalDate?
)
