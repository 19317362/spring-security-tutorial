package com.azizkale.springsecuritytutorial.dao;

import com.azizkale.springsecuritytutorial.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Map<Integer, Employee> employeeMap = new HashMap<>();

    public EmployeeRepositoryImpl() {
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setDepartment("Development");
        employee1.setGender("Male");
        employee1.setName("Aziz Kale");

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setDepartment("Tester");
        employee2.setGender("Female");
        employee2.setName("Ali Veli");

        Employee employee3 = new Employee();
        employee3.setId(3);
        employee3.setDepartment("Human Resource");
        employee3.setGender("Male");
        employee3.setName("Max Musterman");

        employeeMap.put(employee1.getId(),employee1);
        employeeMap.put(employee2.getId(),employee2);
        employeeMap.put(employee3.getId(),employee3);
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public Employee findById(int id) {
        return employeeMap.get(id);
    }

    @Override
    public void create(Employee employee) {
        employee.setId((int) new Date().getTime());
        employeeMap.put(employee.getId(),employee);
    }

    @Override
    public void delete(int id) {
        employeeMap.remove(id);

    }

    @Override
    public Employee update(Employee employee) {
        employeeMap.replace(employee.getId(), employee);
        return employee;
    }
}
