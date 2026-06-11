package com.example.bank_system.Repository;

import com.example.bank_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);
    User findUserByUsername(String username);

}
