package com.example.springBoot_crud.Employee.controllers;

import com.example.springBoot_crud.Employee.models.Employee;
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
    @Autowired
    public EmployeeService employeeService;

    @PostMapping(path = "/saveEmployee")
    public ResponseEntity saveEmployeeToDB(@RequestBody Employee employee, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.status(200).body(employeeService.saveEmployee(employee));
    }

    @GetMapping(path = "/getEmployeeList")
    public List<Employee> getEmployees(){
        return  employeeService.getEmployeeList();
    }

    @GetMapping(path = "/getEmployee/{id}")
    public ResponseEntity getEmployeeByID(@PathVariable("id") int id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return ResponseEntity.status(200).body(employeeService.getEmployeeById(id));
    }

    @PutMapping(path = "/updateEmployee/{id}")
    public ResponseEntity updateEmployee(@PathVariable("id") int id,@RequestBody Employee newEmp,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        Optional<Employee> emp = employeeService.getEmployeeById(id);
        Employee dbEmployee = emp.get();
       if(emp.isPresent()){
            String empName = dbEmployee.getName();
            String empEmail = dbEmployee.getEmail_id();
            if(empName != null){
                dbEmployee.setName(newEmp.getName());
            } else if (empEmail != null) {
                dbEmployee.setEmail_id((newEmp.getEmail_id()));
            }
       }

        return ResponseEntity.status(200).body(employeeService.UpdateEmployee(dbEmployee));
    }

    @DeleteMapping(path="/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable("id") int id){
        Employee emp =employeeService.getEmployeeById(id).orElseThrow(()-> new RuntimeException("Employee with the given ID" + id + "is not available to delete"));
        employeeService.deleteEmployee(id);
    }

}
