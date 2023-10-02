package br.com.vh.employeemanager.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "employees")
@Entity(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String jobTitle;
    private String imageUrl;
    @Column(nullable = false, updatable = false)
    private String employeeCode;

    public Employee(NewEmployeeData data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.jobTitle = data.jobTitle();
        this.imageUrl = data.imageUrl();
        newEmployeeCode();

    }

    public void newEmployeeCode() {
        this.employeeCode = UUID.randomUUID().toString();
    }

    public void updateData(UpdateEmployeeData data) {
        if(data.name() != null) {
            this.name = data.name();
        }
        if(data.email() != null) {
            this.email = data.email();
        }
        if (data.jobTitle() != null){
            this.jobTitle = data.jobTitle();
        }
        if(data.phone() != null) {
            this.jobTitle = data.jobTitle();
        }
        if(data.imageUrl() != null) {
            this.imageUrl = data.imageUrl();
        }
    }

    public Long getId() {
        return this.id;
    }
}

