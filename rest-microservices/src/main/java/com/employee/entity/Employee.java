package com.employee.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Employee")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @NotNull
    @Id
    @Column(name = "id")
    int id;

    @NotNull
    @Column(name="FIRSTNAME")
    String firstName;

    @Column(name="LASTNAME")
    String lastName;

    @Column(name="EMAIL")
    String email;

    @Column(name="PHONENUMBER")
    String phoneNumber;
}
