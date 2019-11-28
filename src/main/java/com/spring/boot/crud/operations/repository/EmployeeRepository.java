package com.spring.boot.crud.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.crud.operations.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT emp FROM Employee emp WHERE emp.firstName like %?1%")
    List<Employee> findByNameContaining(String nom);

    @Query("SELECT emp FROM Employee emp WHERE emp.emailId like %?1")
    List<Employee> findByLastEmailPart(String lastPartOfEmail);

}