package com.example.bank_system.Dto.Out;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTOOut {
    private Integer id;
    private  String phoneNumber;
    private String name;
    private String email;
    private  String username;
    private String role;
    private Set<AccountDTOOut> accounts;

}
