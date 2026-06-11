package com.example.bank_system.Repository;

import com.example.bank_system.Model.Employee;
import com.example.bank_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(Integer id);
}
