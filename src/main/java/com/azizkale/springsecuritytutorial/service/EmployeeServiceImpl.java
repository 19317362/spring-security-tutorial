package com.azizkale.springsecuritytutorial.service;

import com.azizkale.springsecuritytutorial.dao.EmployeeRepository;
import com.azizkale.springsecuritytutorial.exception.EmployeeNotFoundException;
import com.azizkale.springsecuritytutorial.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(id);
        if(employee == null) {
            throw new EmployeeNotFoundException("Employee not found with id :" + id);
        }
        return employee;
    }

    @Override
    public void create(Employee employee) {
        employeeRepository.create(employee);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(int id) {
        employeeRepository.delete(id);
    }

    @Override
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Employee update(Employee employee) {
        return employeeRepository.update(employee);
    }
}
