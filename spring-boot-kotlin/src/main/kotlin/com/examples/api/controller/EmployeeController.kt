package com.examples.api.controller

import com.examples.api.controller.model.EmployeeDto
import com.examples.api.service.EmployeeService
import com.examples.api.service.model.Employee

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RestController

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

/**
 * Controller for REST API endpoints
 */
@RestController
class EmployeeController(private val employeeService: EmployeeService) {

    @GetMapping("/employees")
    fun getAllEmployees(): List<EmployeeDto> {
        return employeeService.getAllEmployees().map {
            EmployeeDto.from(it)
        }
    }

    @GetMapping("/employees/{id}")
    fun getEmployeesById(@PathVariable("id") employeeId: Long): EmployeeDto {
        return EmployeeDto.from(employeeService.getEmployeesById(employeeId))
    }

    @PostMapping("/employees")
    fun createEmployee(@Valid @RequestBody payload: EmployeeDto): EmployeeDto {
        return EmployeeDto.from(employeeService.createEmployee(Employee.from(payload)))
    }

    @PutMapping("/employees/{id}")
    fun updateEmployeeById(@PathVariable("id") employeeId: Long, @Valid @RequestBody payload: EmployeeDto) : EmployeeDto {
        return EmployeeDto.from(employeeService.updateEmployeeById(employeeId, Employee.from(payload)))
    }

    @DeleteMapping("/employees/{id}")
    fun deleteEmployeesById(@PathVariable("id") employeeId: Long): EmployeeDto {
        return EmployeeDto.from(employeeService.deleteEmployeesById(employeeId))
    }
}
