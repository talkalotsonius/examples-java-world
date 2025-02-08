package com.examples.inventory.configuration

import com.examples.inventory.datasource.mock.CsvFileSource

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * This class configures the Spring Beans which are referencing the CSV files with test data.
 */
@Configuration
class TestDataCsvFilesBeanConfig {
//    private val testDataBasePath = "./resources"

    @Bean("carInventoryFileSource")
    fun carInventoryFileSource() = CsvFileSource("inventory.csv")
}