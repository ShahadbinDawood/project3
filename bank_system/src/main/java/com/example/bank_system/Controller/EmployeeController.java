package com.example.bank_system.Controller;

import com.example.bank_system.Dto.In.EmployeeDTOIn;
import com.example.bank_system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    // ADMIN only
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeDTOIn employeeIn) {
        employeeService.registerEmployee(employeeIn);
        return ResponseEntity.status(201).body("Employee registered successfully");
    }

    // ADMIN only
    @GetMapping("")
    public ResponseEntity<?> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    // ADMIN only
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    // ADMIN only
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Integer id,
                                            @RequestBody EmployeeDTOIn employeeIn) {
        employeeService.UpdateEmployee(id, employeeIn);
        return ResponseEntity.ok("Employee updated successfully");
    }

    // ADMIN only
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}

