package com.example.bank_system.Dto.Out;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTOOut {

    private Integer id;
    private String position;
    private Integer salary;
    private String name;      // من User
    private String email;     // من User
    private  String username;
    private String role;
}
