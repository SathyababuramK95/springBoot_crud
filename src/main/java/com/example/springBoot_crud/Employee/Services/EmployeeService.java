package com.example.springBoot_crud.Employee.Services;

import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.springBoot_crud.Employee.models.Employee;
import com.example.springBoot_crud.Employee.repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    public EmployeeRepository employeeRepository;

    public List<Employee> getEmployeeList(){
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee){
//        employee.setId();
        return  employeeRepository.save(employee);

//        return new ResponseEntity<Authenticator.Success>(HttpStatus.ACCEPTED);
    }

    public Optional<Employee> getEmployeeById(int id){
        return employeeRepository.findById(id);
    }

    public Employee UpdateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(int id){
        employeeRepository.deleteById(id);
    }
}
