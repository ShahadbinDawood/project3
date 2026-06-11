package com.example.bank_system.service;

import com.example.bank_system.Api.ApiException;
import com.example.bank_system.Dto.In.CustomerDTOIn;
import com.example.bank_system.Dto.Out.CustomerDTOOut;
import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.User;
import com.example.bank_system.Repository.CustomerRepository;
import com.example.bank_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void registerCustomer(CustomerDTOIn customerIn) {
        User user = new User(null, customerIn.getUsername(), passwordEncoder.encode(customerIn.getPassword()), customerIn.getName(), customerIn.getEmail(), "CUSTOMER", null, null);

        userRepository.save(user);
        Customer customer = new Customer();
        customer.setPhoneNumber(customerIn.getPhoneNumber());
        customer.setUser(user);
        customerRepository.save(customer);

    }

    public List<CustomerDTOOut> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(c -> new CustomerDTOOut(
                        c.getId(),
                        c.getPhoneNumber(),
                        c.getUser().getName(),
                        c.getUser().getEmail(),
                        c.getUser().getUsername(),
                        c.getUser().getRole(),
                        null
                ))
                .collect(Collectors.toList());

    }

    public void UpdateCustomer(Integer id, CustomerDTOIn customerIn) {
        Customer customer =customerRepository.findCustomerById(id);
        if (customer == null) throw new ApiException("Customer not found");
        customer.setPhoneNumber(customerIn.getPhoneNumber());
        customer.getUser().setName(customerIn.getName());
        customer.getUser().setEmail(customerIn.getEmail());
        customer.getUser().setPassword(passwordEncoder.encode(customerIn.getPassword()));

        customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) throw new ApiException("Customer not found");
        customerRepository.delete(customer);
    }

    public CustomerDTOOut getCustomerById(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) throw new ApiException("Customer not found");
        return new CustomerDTOOut(customer.getId(),
                customer.getPhoneNumber(),
                customer.getUser().getName(),
                customer.getUser().getEmail(),
                customer.getUser().getUsername(),
                customer.getUser().getRole(),
                null);
    }

}
