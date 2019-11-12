package com.spring.boot.crud.operations.service;

import com.spring.boot.crud.operations.exception.ResourceNotFoundException;
import com.spring.boot.crud.operations.model.Employee;
import com.spring.boot.crud.operations.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> listEmployee = employeeRepository.findAll();
        // use the stream method
//		return listEmployee.stream().filter(emp -> emp.getFirstName().equals("moussi") && emp.getEmailId().equals("msi.moussi@gmail.com")).collect(Collectors.toList());
        return listEmployee;
    }

    @Override
    public Employee getEmployeeById(@NotNull Long employeeId) throws ResourceNotFoundException {
        return getEmployee(employeeId);
    }

    @Override
    public Employee createEmployee(@NotNull Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(@NotNull Long employeeId, @NotNull Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = getEmployee(employeeId);

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        return employeeRepository.save(employee);
    }

    @Override
    public Map<String, Boolean> deleteEmployee(@NotNull Long employeeId) throws ResourceNotFoundException {
        Employee employee = getEmployee(employeeId);

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    private Employee getEmployee(Long employeeId) throws ResourceNotFoundException {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    }
}
