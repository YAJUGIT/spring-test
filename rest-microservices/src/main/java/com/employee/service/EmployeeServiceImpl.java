package com.employee.service;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeError;
import com.employee.exception.EmployeeException;
import com.employee.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public void createEmployee(Employee employee) throws EmployeeException {
        employeeRepo.save(employee);
    }

    @Override
    public Optional<Employee> getEmployee(int id) throws EmployeeException {
         return employeeRepo.findById(id);
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeException {
            employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployee(int id) throws EmployeeException {
            employeeRepo.deleteById(id);
    }

    /*
        @Override
        public Optional<List<Employee>> getEmployee(Long id) throws EmployeeException {
            return employeeRepo.findById(id);
        }

        @Override
        public void updateEmployee(EmployeeDTO employeeDTO) throws EmployeeException {
            Optional<Employee> existingSortPref = sortPrefRepo.findById(employeeDTO.getId());
            Employee sortPref = null;
            if (existingSortPref.isPresent()) {
                sortPref = existingSortPref.get();
                sortPref.setUserId(employeeDTO.getUserId());
                sortPref.setSortState(UserPrefUtil.getJsonData(employeeDTO.getSortState()));
                sortPref.setFilterStates(UserPrefUtil.getJsonData(employeeDTO.getFilterStates()));
                sortPref.setColState(UserPrefUtil.getJsonData(employeeDTO.getColState()));
                sortPrefDao.updateSortPref(sortPref);
            }
        }

        @Override
        public void deleteEmployee(Long id) throws EmployeeException {
            sortPrefRepo.deleteById(id);
        }
    */
    private Employee getCustomJob() {
        return new Employee();
    }
}
