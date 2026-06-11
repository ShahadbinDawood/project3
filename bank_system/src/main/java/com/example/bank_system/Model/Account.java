package com.example.bank_system.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false )
    private  String accountNumber;
    @Column(columnDefinition = "int not null CHECK (balance >= 0)")
    private Integer balance;
    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE NOT NULL ")
    private boolean isActive ;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
