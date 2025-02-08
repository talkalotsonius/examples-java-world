package com.examples.api.service

import com.examples.api.exception.EmployeeNotFoundException
import com.examples.api.repository.model.EmployeeEntity
import com.examples.api.repository.EmployeeRepository
import com.examples.api.service.model.Employee

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

/**
 * Service for interactions with employee domain object
 */
@Service
class EmployeeService(private val employeeRepository: EmployeeRepository) {

    fun getAllEmployees(): List<Employee> {
        return employeeRepository.findAll().map {
            Employee.from(it)
        }
    }

    fun getEmployeesById(employeeId: Long): Employee {
        return employeeRepository.findById(employeeId).orElse(null)?.let {
            Employee.from(it)
        } ?: throw EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found") //handle exception in controller
    }

    fun createEmployee(employee: Employee): Employee {
        return Employee.from(employeeRepository.save(EmployeeEntity.from(employee)))
    }

    fun updateEmployeeById(employeeId: Long, employee: Employee): Employee {
        return if (employeeRepository.existsById(employeeId)) {
            Employee.from(employeeRepository.save(EmployeeEntity.from(employee)))
        } else throw EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found")
    }

    fun deleteEmployeesById(employeeId: Long): Employee {
        return employeeRepository.findById(employeeId).orElse(null)?.let {
            employeeRepository.delete(it)
            Employee.from(it)
        } ?: throw EmployeeNotFoundException(HttpStatus.NOT_FOUND, "No matching employee was found")
    }
}
