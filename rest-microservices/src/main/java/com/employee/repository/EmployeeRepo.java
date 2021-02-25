package com.employee.repository;

import com.employee.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends CrudRepository<Employee,Integer> {

    @Query(value = "SELECT e FROM Employee e WHERE e.firstName LIKE ?1")
    List<Employee> findEmployeesWithNameLike(String name);
}
