package com.employee;


import com.employee.repository.EmployeeRepositoryTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = {EmployeeApplication.class, EmployeeRepositoryTestConfiguration.class})
@ContextConfiguration
@ActiveProfiles("test")
public class EmployeeApplicationTest {
    @Test
    public void contextLoads() {
        System.out.println("Hi Context load");
    }
}
