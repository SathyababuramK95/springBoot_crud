package com.example.springBoot_crud.Employee.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@Table(name = "system_details")
@SequenceGenerator(name = "sys_seq",sequenceName = "system_sequence",
        initialValue = 1,allocationSize = 1)
public class System_Details implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "sys_seq")
    private int id;
    private String system_name;
    private String system_no;
    private String system_os;

    @JsonIgnore
    @OneToOne(mappedBy = "system_details")
    private Employee employee;


}
