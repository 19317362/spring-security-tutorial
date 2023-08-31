package com.azizkale.springsecuritytutorial.service;

import com.azizkale.springsecuritytutorial.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    void create(Employee employee);
    void delete (int id);
    Employee update (Employee employee);
}
