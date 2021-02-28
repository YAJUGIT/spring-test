package com.employee.controller.impl;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import com.employee.util.EmployeeUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerImplIT {
    EmployeeDTO employeeDTO;
    Employee employee;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    private static Object getObjectFromJson(String jsonString) {
        Employee employee = new Employee();
        try {
            JSONObject jsonObject = EmployeeUtil.getJSonObject(jsonString);
            employee.setId(jsonObject.getInt("id"));
            employee.setFirstName(jsonObject.getString("firstName"));
            employee.setLastName(jsonObject.getString("lastName"));
            employee.setEmail(jsonObject.getString("email"));
            employee.setPhoneNumber(jsonObject.getString("phoneNumber"));
        } catch (JSONException ioException) {
            System.err.println(ioException);
        }
        return employee;
    }

    @BeforeEach
    void setUp() {
        employeeDTO = EmployeeDTO.builder()
                .id(1)
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("465-222-111")
                .email("abc@abc.com")
                .build();
        employee = EmployeeUtil.getEmployee(employeeDTO);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createEmployee() throws Exception {
        doNothing().when(employeeService).createEmployee(employee);

        //When
        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(EmployeeUtil.asJsonString(employeeDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("GET /employee/1")
    void getEmployee() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("userId", "abc@abc.com");
        //When
        doReturn(Optional.ofNullable(employee)).when(employeeService).getEmployee(1);
        MvcResult result = this.mockMvc.perform(get("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(print()).andReturn();

        Employee employee1 = (Employee) getObjectFromJson(result.getResponse().getContentAsString());
        assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());
        assertEquals(employee1.getId(), employee.getId());
        assertEquals(employee1.getFirstName(), employee.getFirstName());
        assertEquals(employee1.getLastName(), employee.getLastName());
        assertEquals(employee1.getEmail(), employee.getEmail());

        //then
        verify(employeeService, times(1)).getEmployee(1);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    @DisplayName("GET /employees success")
    void testGetWidgetsSuccess() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("userId", "abc@abc.com");
        // Setup our mocked service
        Employee employee1 = new Employee(1, "Stephen", "Colbert", "sbc.amail.com", "234-345-567");
        Employee employee2 = new Employee(2, "Rama", "Chandra", "sbc.amail.com", "234-345-567");
        //when
        doReturn(Arrays.asList(employee1, employee2)).when(employeeService).findAll();
        // Execute the GET request
        mockMvc.perform(get("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))

                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                // Validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/employees"))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @DisplayName("GET /employee/4 - Not Found")
    void testGetEmployeeByIdNotFound() throws Exception {
        // Setup our mocked service
        doReturn(Optional.empty()).when(employeeService).getEmployee(4);

        // Execute the GET request
        mockMvc.perform(get("/employee/{id}", 4))
                // Validate the response code
                .andExpect(status().isNotFound());
    }

    @Test
    void updateEmployee() {

    }

    @Test
    void deleteEmployee() {
    }
}