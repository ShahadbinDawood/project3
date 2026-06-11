package com.example.bank_system.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(10) not null unique"  )
    private  String username;
    @Column(columnDefinition = "VARCHAR(150) ")
    private String password;
    @Column(columnDefinition = "varchar(20) not null "  )
    private  String name;
    @Column(nullable = false)
    private String email;
    @Column(columnDefinition = "VARCHAR(8) CHECK (role = 'CUSTOMER' or role ='EMPLOYEE' or role='ADMIN' )")
    private String role;
    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private Customer customer ;
    @OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
