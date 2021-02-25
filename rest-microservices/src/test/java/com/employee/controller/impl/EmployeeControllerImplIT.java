package com.employee.controller.impl;

import com.employee.dto.EmployeeDTO;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import com.employee.util.EmployeeUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
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
//        MockitoAnnotations.initMocks(this);
        employeeService = mock(EmployeeService.class);
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

        //then
        verify(employeeService, times(1)).createEmployee(employee);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void getEmployee() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add("userId", "abc@abc.com");
        //When
        when(employeeService.getEmployee(1)).thenReturn(Optional.ofNullable(employee));
        MvcResult result = mockMvc.perform(get("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(headers))
                .andDo(print()).andReturn();

        Employee employee1 = (Employee) getObjectFromJson(result.getResponse().getContentAsString());
        //assertTrue(employee1.getId(), employee.getId());
        assertTrue(employee1.getFirstName().equalsIgnoreCase(employee.getFirstName()));
        assertTrue(employee1.getLastName().equalsIgnoreCase(employee.getLastName()));
//        assertEquals(employee1.getEmail(), employee.getEmail());

        //then
        verify(employeeService, times(1)).getEmployee(1);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void updateEmployee() {

    }

    @Test
    void deleteEmployee() {
    }
}