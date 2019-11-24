package com.spring.boot.crud.operations.service;

import com.spring.boot.crud.operations.exception.ResourceNotFoundException;
import com.spring.boot.crud.operations.model.Employee;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee getEmployeeById(@NotNull Long employeeId) throws ResourceNotFoundException;

    Employee createEmployee(@NotNull Employee employee);

    Employee updateEmployee(@NotNull Long employeeId, @NotNull Employee employeeDetails) throws ResourceNotFoundException;

    Map<String, Boolean> deleteEmployee(@NotNull Long employeeId) throws ResourceNotFoundException;

//    List<Employee> getEmployeesByNom(@NotEmpty String employeeNom);

}
