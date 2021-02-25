package com.employee.unit;

import com.employee.controller.EmployeeController;
import com.employee.controller.impl.EmployeeControllerImpl;
import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
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

    public EmployeeControllerTest() {
        employeeService = Mockito.mock(EmployeeService.class);
        employeeController = new EmployeeControllerImpl(employeeService);
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .build();
    }

    @Test
    public void whenEmployeeNotExists_thenHttp404() throws Exception {
        Mockito.doReturn(Optional.empty())
                .when(employeeService)
                .getEmployee(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void whenEmployeeExists_thenHttp200_andDoctorReturned() throws Exception {
        Employee employee = new Employee(1, "Stephen", "Colbert", "sbc.amail.com", "234-345-567");
        Mockito.doReturn(Optional.of(employee))
                .when(employeeService)
                .getEmployee(1);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1"))
                .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
                .andExpect(MockMvcResultMatchers.content().string(containsString("Stephen")));
    }
}
