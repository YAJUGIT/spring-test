package com.employee.unit;

import com.employee.controller.EmployeeController;
import com.employee.controller.impl.EmployeeControllerImpl;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import com.employee.util.EmployeeUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.core.StringContains.containsString;

public class EmployeeControllerTest {
    EmployeeDTO employeeDTO;
    Employee employee;
    EmployeeController employeeController;
    EmployeeService employeeService;
    MockMvc mockMvc;

    EmployeeControllerTest() {
        employeeService = Mockito.mock(EmployeeService.class);
        employeeController = new EmployeeControllerImpl(employeeService);
    }

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();

        employeeDTO = EmployeeDTO.builder()
                .id(1)
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("465-222-111")
                .email("abc@abc.com")
                .build();
        employee = EmployeeUtil.getEmployee(employeeDTO);
    }

    @Test
    void whenEmployeeNotExists_thenHttp404() throws Exception {
        Mockito.doReturn(Optional.empty())
                .when(employeeService)
                .getEmployee(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void whenEmployeeExists_thenHttp200_andEmployeeReturned() throws Exception {
        Employee employee = new Employee(1, "Stephen", "Colbert", "sbc.amail.com", "234-345-567");
        Mockito.doReturn(Optional.of(employee))
                .when(employeeService)
                .getEmployee(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Stephen")));
    }

    @Test
    void createEmployee() throws Exception {
        Mockito.doNothing().when(employeeService).createEmployee(employee);
        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(EmployeeUtil.asJsonString(employeeDTO)))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()));
    }
}
