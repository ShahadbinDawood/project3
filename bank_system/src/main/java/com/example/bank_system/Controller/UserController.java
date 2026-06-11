package com.example.bank_system.Controller;

import com.example.bank_system.Dto.In.UserDTOIn;
import com.example.bank_system.Dto.Out.UserDTOOut;
import com.example.bank_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // ADMIN only
    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    // ADMIN only
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,
                                             @RequestBody UserDTOIn userIn) {
        userService.updateUser(id, userIn);
        return ResponseEntity.ok("User updated successfully");
    }

    // ADMIN only
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}