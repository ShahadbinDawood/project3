package com.example.bank_system.Dto.In;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTOIn {
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
    @Pattern(regexp =  "(?i)^(CUSTOMER|EMPLOYEE|ADMIN)$")
    private String role;



}
