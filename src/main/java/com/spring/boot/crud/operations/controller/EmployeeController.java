package com.spring.boot.crud.operations.controller;

import com.spring.boot.crud.operations.exception.MohamedException;
import com.spring.boot.crud.operations.exception.ResourceNotFoundException;
import com.spring.boot.crud.operations.model.Employee;
import com.spring.boot.crud.operations.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees().parallelStream().map(user -> {
            user.setFirstName(user.getFirstName() + user.getId());
            return user;
        }).collect(Collectors.toList());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        employeeService.defaultInterface(employeeId);
        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployeeById(employeeId));
        ResponseEntity<Employee> responseEntity = null;
        if (employee.isPresent()) {
            responseEntity = ResponseEntity.ok().body(employee.get());
        }
        return responseEntity;
    }

    @GetMapping("/employees/containssearch/{nom}")
    public List<Employee> getEmployeeByNom(@PathVariable(value = "nom") String employeeNom) {
        return employeeService.getEmployeeByNameContaining(employeeNom);
    }

    @GetMapping("/employees/endsbysearch/{lastEmailPart}")
    public List<Employee> getEmployeeByNameContaining(@PathVariable(value = "lastEmailPart") String lastEmailPart) {
        return employeeService.findByLastEmailPart(lastEmailPart);
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) throws MohamedException {
        Optional<Employee> emp = Optional.ofNullable(employeeService.createEmployee(employee));
        if (emp.isEmpty()) {
            throw new MohamedException();
        }
        return emp.get();
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeDetails));
    }

    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        return employeeService.deleteEmployee(employeeId);
    }
}
