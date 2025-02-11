package com.examples.inventory.controller

import com.examples.inventory.service.CarService
import com.examples.inventory.utils.CarFaker

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class CarControllerWebMvcTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var carService: CarService

    @Test
    fun `Given valid url, when GET car list is called, then returns 200 `() {
        val carFakerList = CarFaker.fakeCarList()

        Mockito.`when`(carService.getAllCars()).thenReturn(carFakerList)

        mockMvc.perform(get("/cars")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$").exists())
            .andExpect(jsonPath("$.[*].id").isNotEmpty)
    }

}