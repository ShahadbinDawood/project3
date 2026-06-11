package com.example.bank_system.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    private Integer id;
    @Column(nullable = false )
    private  String phoneNumber;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<Account> accounts;
}
