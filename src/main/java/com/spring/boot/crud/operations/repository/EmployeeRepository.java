package com.spring.boot.crud.operations.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.boot.crud.operations.model.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @Query("select emp from Employee emp where emp.firstName like %?1")
//    List<Employee> findByNom(String nom);

}
