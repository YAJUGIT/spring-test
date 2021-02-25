package com.employee.repository;

import com.employee.entity.Employee;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.DBUnitExtension;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DBUnitExtension.class)
@SpringBootTest
//@ActiveProfiles("test")
class EmployeeRepoTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    EmployeeRepo employeeRepo;

    public ConnectionHolder getConnectionHolder() {
        return () -> dataSource.getConnection();
    }

    @Test
    @DataSet("employee.yml")
    void testFindAll() {
        List<Employee> widgets = Lists.newArrayList(employeeRepo.findAll());
        Assertions.assertEquals(2, widgets.size(), "Expected 2 employee in the database");
    }
}