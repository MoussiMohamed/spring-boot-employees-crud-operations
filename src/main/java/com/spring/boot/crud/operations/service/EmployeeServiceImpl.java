package com.spring.boot.crud.operations.service;

import com.spring.boot.crud.operations.exception.ResourceNotFoundException;
import com.spring.boot.crud.operations.model.Employee;
import com.spring.boot.crud.operations.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Employee getEmployeeById(Long employeeId) throws ResourceNotFoundException {
        return getEmployee(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = getEmployee(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        return employeeRepository.save(employee);
    }

    @Override
    public Map<String, Boolean> deleteEmployee(Long employeeId) {
        Optional<Employee> employee = getEmployee(employeeId);

        Map<String, Boolean> response = new HashMap<>();
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            response.put("deleted", Boolean.TRUE);
        }
        return response;
    }

    @Override
    public List<Employee> getEmployeeByNameContaining(String employeeNom) {
        return employeeRepository.findByNameContaining(employeeNom);
    }

    private Optional<Employee> getEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public List<Employee> findByLastEmailPart(String lastEmailPart) {
        return employeeRepository.findByLastEmailPart(lastEmailPart);
    }

}
