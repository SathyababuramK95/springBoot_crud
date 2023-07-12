package com.example.springBoot_crud.Employee.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Getter
@Setter
@Table(name = "employee")
@SequenceGenerator(name = "emp_seq",sequenceName = "employee_sequence",
        initialValue = 1,allocationSize = 1)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "emp_seq")
    private int id;
    @Column(nullable = false)
    private String name;
    private String designation;
    @Column(nullable = false)
    private String date_of_joining;
    private String date_of_birth;
    private int age;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "system_id",referencedColumnName = "id")
    private System_Details system_details;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id",referencedColumnName = "id")
    private List<Employee_Address> address = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "Employee_Roles",
    joinColumns = {
            @JoinColumn(name = "emp_id",referencedColumnName = "id")
    },inverseJoinColumns = {
            @JoinColumn(name = "role_id",referencedColumnName = "id")
    })
    private List<Roles> roles;

//    public Employee(){}
//    public Employee(String name, String designation,String date_of_joining,String date_of_birth,
//                    int age,String email_id, System_Details system_details){
//        this.name = name;
//        this.designation = designation;
//        this.date_of_joining = date_of_joining;
//        this.date_of_birth = date_of_birth;
//        this.age = age;
//        this.email_id = email_id;
//        this.system_details = system_details;
//        this.system_details.setEmployee(this);
//    }

}
