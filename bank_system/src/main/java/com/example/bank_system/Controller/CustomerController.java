package com.example.bank_system.Controller;

import com.example.bank_system.Dto.In.CustomerDTOIn;
import com.example.bank_system.Dto.Out.CustomerDTOOut;
import com.example.bank_system.Model.User;
import com.example.bank_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    // permit all
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerDTOIn customerIn) {
        customerService.registerCustomer(customerIn);
        return ResponseEntity.status(201).body("Customer registered successfully");
    }

    // ADMIN only
    @GetMapping("")
    public ResponseEntity<List<?>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomer());
    }

    // ADMIN only
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    // CUSTOMER only - يعدل بياناته بس
    @PutMapping("")
    public ResponseEntity<?> updateCustomer(@AuthenticationPrincipal User user, @RequestBody CustomerDTOIn customerIn) {
        customerService.UpdateCustomer(user.getId(), customerIn);
        return ResponseEntity.ok("Customer updated successfully");
    }

    // ADMIN only
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}