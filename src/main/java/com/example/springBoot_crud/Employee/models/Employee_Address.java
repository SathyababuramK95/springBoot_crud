package com.example.springBoot_crud.Employee.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@Table(name = "address")
public class Employee_Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "address_sequence")

    private int id;
    private String street_details;
    private String city;
    private String district;
    private String state;
    private int pin_code;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Employee employee;
}
