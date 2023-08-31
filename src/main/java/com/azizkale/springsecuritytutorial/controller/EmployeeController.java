package com.azizkale.springsecuritytutorial.controller;

import com.azizkale.springsecuritytutorial.service.EmployeeService;
import com.azizkale.springsecuritytutorial.exception.EmployeeNotFoundException;
import com.azizkale.springsecuritytutorial.exception.InternalServerException;
import com.azizkale.springsecuritytutorial.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //ResponseEntity ile ayni sonuc döner fakat dönen bilgiler daha acik ifade edilir (response code:200 gibi)
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> get(){
        List<Employee> employees = employeeService.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> find(@PathVariable int id){
        try {
            Employee employee = employeeService.findById(id);
            return ResponseEntity.ok(employee);
        }
        catch (EmployeeNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/employee")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        try {
            employeeService.create(employee);
            Integer id = employee.getId();
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(location).build();
        } catch (InternalServerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/employee")
    public ResponseEntity<?> update(@RequestBody Employee employee){
        try{
            employeeService.update(employee);
            return ResponseEntity.ok(employee);
        } catch(EmployeeNotFoundException ex){
            return ResponseEntity.notFound().build();
        } catch(Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> delete(@PathVariable int id){
        try{
            employeeService.delete(id);
            return ResponseEntity.ok().build();
        }catch (EmployeeNotFoundException ex){
            return ResponseEntity.notFound().build();
        }catch (InternalServerException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}