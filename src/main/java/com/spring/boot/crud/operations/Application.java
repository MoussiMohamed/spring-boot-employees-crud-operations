package com.spring.boot.crud.operations;

import com.spring.boot.crud.operations.model.Employee;
import com.spring.boot.crud.operations.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private EmployeeService employeeService;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String...args) throws Exception {
		logger.info("Start Init Employees");
		Employee emp = new Employee();
		emp.setFirstName("Mohamed");
		emp.setLastName("Moussi");
		emp.setEmailId("msi.moussi@gmail.com");
		employeeService.createEmployee(emp);
		logger.info("End Init Employees");
	}
}
