package com.example.springBoot_crud.Employee.controllers;

import com.example.springBoot_crud.Employee.EmployeeNotFoundException;
import com.example.springBoot_crud.Employee.models.Employee;
import com.example.springBoot_crud.Employee.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.springBoot_crud.Employee.Services.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    public EmployeeService employeeService;

    @Autowired
    public JwtUtils jwtUtils;

    @PostMapping(path = "/saveEmployee")
    public ResponseEntity saveEmployeeToDB(@RequestBody Employee employee, BindingResult bindingResult){
        logger.info("saving employee details" + employee);

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        employee.setPassword(jwtUtils.EncryptString(employee.getPassword()));
        return ResponseEntity.status(200).body(employeeService.saveEmployee(employee));
    }

    @GetMapping(path = "/getEmployeeList")
    public List<Employee> getEmployees() {
        logger.info("getting employee lists");
        return  employeeService.getEmployeeList();
    }

    @GetMapping(path = "/getEmployee/{id}")
    public ResponseEntity getEmployeeByID(@PathVariable("id") Integer id){
        Optional<Employee> emp  = employeeService.getEmployeeById(id);
        if(emp.isEmpty()){
            throw new EmployeeNotFoundException("Employee not found");
        }
        return ResponseEntity.status(200).body(employeeService.getEmployeeById(id));
    }

    @PutMapping(path = "/updateEmployee/{id}")
    public ResponseEntity updateEmployee(@PathVariable("id") int id,@RequestBody Employee newEmp){
        logger.info("employee trying to update",newEmp);
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        if(emp.isEmpty()){
            throw new EmployeeNotFoundException("Employee not found");
        }
        Employee dbEmployee = emp.get();
        String empName = dbEmployee.getName();
        String empEmail = dbEmployee.getEmail();
        if(empName != null){
            dbEmployee.setName(newEmp.getName());
        } else if (empEmail != null) {
            dbEmployee.setEmail((newEmp.getEmail()));
        }
        return ResponseEntity.status(200).body(employeeService.UpdateEmployee(dbEmployee));
    }

    @DeleteMapping(path="/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable("id") int id){
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        if(emp.isEmpty()){
            throw new EmployeeNotFoundException("Employee not found");
        }
        employeeService.deleteEmployee(id);
    }

}
