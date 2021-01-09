package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    @NotNull
    int id;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    String phoneNumber;
    @NotNull
    String email;

   }
