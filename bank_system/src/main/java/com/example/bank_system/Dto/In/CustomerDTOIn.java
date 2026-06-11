package com.example.bank_system.Dto.In;


import com.example.bank_system.Model.Account;
import com.example.bank_system.Model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTOIn {
    @NotNull(message = "user Id  can not be empty")
    private Integer userId;
    @NotEmpty(message = "phoneNumber can not be empty")
    @Pattern(regexp = "^05\\d*", message = "The value must start with 05")
    private  String phoneNumber;
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
