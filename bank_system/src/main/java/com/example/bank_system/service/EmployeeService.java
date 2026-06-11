package com.example.bank_system.service;


import com.example.bank_system.Api.ApiException;
import com.example.bank_system.Dto.In.EmployeeDTOIn;
import com.example.bank_system.Dto.Out.EmployeeDTOOut;
import com.example.bank_system.Model.Employee;
import com.example.bank_system.Model.User;
import com.example.bank_system.Repository.EmployeeRepository;
import com.example.bank_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerEmployee(EmployeeDTOIn employeeIn) {
        User user = new User(null, employeeIn.getUsername(), passwordEncoder.encode(employeeIn.getPassword()), employeeIn.getName(), employeeIn.getEmail(), "EMPLOYEE", null, null);
        userRepository.save(user);
        Employee employee = new Employee();
        employee.setUser(user);
        employee.setPosition(employeeIn.getPosition());
        employee.setSalary(employeeIn.getSalary());
        employeeRepository.save(employee);

    }

    public void UpdateEmployee(Integer id, EmployeeDTOIn employeeIn) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) throw new ApiException("Employee not found");
        employee.setPosition(employeeIn.getPosition());
        employee.setSalary(employeeIn.getSalary());
        employee.getUser().setName(employeeIn.getName());
        employee.getUser().setEmail(employeeIn.getEmail());
        employee.getUser().setPassword(passwordEncoder.encode(employeeIn.getPassword()));

        employeeRepository.save(employee);
    }

    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) throw new ApiException("Employee not found");
        employeeRepository.delete(employee);
    }

    public EmployeeDTOOut getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) throw new ApiException("Employee not found");
        return new EmployeeDTOOut(employee.getId(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getUser().getName(),
                employee.getUser().getEmail(),
                employee.getUser().getUsername(),
                employee.getUser().getRole());
    }

    public List<EmployeeDTOOut> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(e -> new EmployeeDTOOut(
                        e.getId(),
                        e.getPosition(),
                        e.getSalary(),
                        e.getUser().getName(),
                        e.getUser().getEmail(),
                        e.getUser().getUsername(),
                        e.getUser().getRole()
                ))
                .collect(Collectors.toList());

    }


}
