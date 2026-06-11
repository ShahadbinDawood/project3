package com.example.bank_system.Dto.In;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTOIn {
    @NotNull(message = "user Id  can not be empty")
    private Integer userId;
    @NotEmpty(message = "position can not be empty")
    private  String position;
    @NotNull(message = "salary can not be empty")
    @PositiveOrZero
    private Integer salary;
    @NotEmpty(message = "user name can not be empty")
    @Size(min = 4 , max = 10)
    private String username;
    @Min(6)
    @NotEmpty(message = "password can not be empty")
    private String password;
    @Size(min = 2 , max = 20)
    @NotEmpty(message = "name can not be empty")
    private  String name;
    @Email
    private String email;



}
