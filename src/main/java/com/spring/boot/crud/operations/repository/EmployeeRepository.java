package com.spring.boot.crud.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.crud.operations.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
