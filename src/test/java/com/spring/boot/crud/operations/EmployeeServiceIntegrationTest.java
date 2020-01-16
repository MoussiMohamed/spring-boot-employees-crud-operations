package com.spring.boot.crud.operations;

import com.spring.boot.crud.operations.controller.EmployeeController;
import com.spring.boot.crud.operations.exception.ResourceNotFoundException;
import com.spring.boot.crud.operations.model.Employee;
import com.spring.boot.crud.operations.repository.EmployeeRepository;
import com.spring.boot.crud.operations.service.EmployeeService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@ContextConfiguration(locations = { "classpath:spring-test-config.xml" })
public class EmployeeServiceIntegrationTest {

	@Autowired
	EmployeeService employeeService;

	/**
	 * Tester la recuperation d'un employé par son ID
	 * @throws ResourceNotFoundException
	 */
	@Test
	public void whenFindById_thenReturnEmployee() throws ResourceNotFoundException {
		// given
		Employee med = new Employee();
		med.setFirstName("med");
		med.setLastName("moussi");
		med.setEmailId("msi.moussi@gmail.com");
		employeeService.createEmployee(med);

		// when
		Employee found = employeeService.getEmployeeById(1L);

		// then
		assertThat(found.getFirstName())
				.isEqualTo(med.getFirstName());
		assertThat(found.getLastName())
				.isEqualTo(med.getLastName());
		assertThat(found.getEmailId())
				.isEqualTo(med.getEmailId());
	}

}
