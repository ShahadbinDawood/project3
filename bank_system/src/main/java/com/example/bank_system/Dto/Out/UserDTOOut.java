package com.example.bank_system.Dto.Out;

import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTOOut {

    private Integer id;
    private  String username;
    private  String name;
    private String email;
    private String role;



}
