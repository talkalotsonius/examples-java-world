package com.examples.inventory.datasource.database.repository

import com.examples.inventory.datasource.database.model.CarEntity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository: JpaRepository<CarEntity, Int> {
}