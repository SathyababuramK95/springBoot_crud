package com.example.springBoot_crud.Employee.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "roles_sequence")
    private int id;

    private String role_name;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles")
//    private List<Employee> employeeList;
}
