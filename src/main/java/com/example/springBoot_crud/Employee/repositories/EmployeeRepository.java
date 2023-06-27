package com.example.springBoot_crud.Employee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springBoot_crud.Employee.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

}
