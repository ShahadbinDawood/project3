package com.example.bank_system.Repository;

import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository  extends JpaRepository<Customer, Integer> {
    Customer findCustomerById(Integer id);

    Customer findCustomersByUser_Id(Integer userId);
}
