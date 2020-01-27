package com.spring.boot.crud.operations;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.boot.crud.operations.exception.ResourceNotFoundException;
import com.spring.boot.crud.operations.model.Employee;
import com.spring.boot.crud.operations.repository.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeServiceUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeRepository mockEmployeeRepository;
	
	@Before
    public void init() {
        Employee employee = new Employee(18L, "Mohamed", "Moussi", "msi.moussi@gmail.com");
        when(mockEmployeeRepository.findById(18L)).thenReturn(Optional.of(employee));
    }

	/**
	 * Tester la recuperation d'un employé par son ID
	 * 
	 * @throws Exception
	 * @throws Exception
	 */
	@Test
    public void find_EmployeeById_OK() throws Exception {

        mockMvc.perform(get("/api/v1/employees/18"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(18)))
                .andExpect(jsonPath("$.firstName", is("Mohamed")))
                .andExpect(jsonPath("$.lastName", is("Moussi")))
                .andExpect(jsonPath("$.emailId", is("msi.moussi@gmail.com")));

        verify(mockEmployeeRepository, times(1)).findById(18L);

    }

}
