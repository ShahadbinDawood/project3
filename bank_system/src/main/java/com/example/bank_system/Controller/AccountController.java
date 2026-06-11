package com.example.bank_system.Controller;

import com.example.bank_system.Dto.In.AccountDTOIn;
import com.example.bank_system.Model.User;
import com.example.bank_system.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    // ADMIN only
    @GetMapping("")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccount());
    }

    // ADMIN, EMPLOYEE
    @GetMapping("/{id}")
    public ResponseEntity<?> viewAccountDetails(@PathVariable Integer id) {
        return ResponseEntity.ok(accountService.viewAccountDetails(id));
    }

    // EMPLOYEE only
    @PostMapping("")
    public ResponseEntity<String> createAccount(@RequestBody AccountDTOIn accountIn) {
        accountService.CreateAccount(accountIn);
        return ResponseEntity.status(201).body("Account created successfully");
    }

    // CUSTOMER only
    @PutMapping("/{accountId}")
    public ResponseEntity<String> updateAccount(@PathVariable Integer accountId,
                                                @RequestBody AccountDTOIn accountIn,
                                                @AuthenticationPrincipal User user) {
        accountService.updateAccount(accountId, accountIn, user);
        return ResponseEntity.ok("Account updated successfully");
    }

    // CUSTOMER only
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Integer accountId,
                                                @AuthenticationPrincipal User user) {
        accountService.deleteAccount(accountId, user);
        return ResponseEntity.ok("Account deleted successfully");
    }

    // EMPLOYEE only
    @PutMapping("/activate/{id}")
    public ResponseEntity<String> activateAccount(@PathVariable Integer id) {
        accountService.activateAccount(id);
        return ResponseEntity.ok("Account activated successfully");
    }

    // EMPLOYEE only
    @PutMapping("/block/{id}")
    public ResponseEntity<?> blockAccount(@PathVariable Integer id) {
        accountService.blockAccount(id);
        return ResponseEntity.ok("Account blocked successfully");
    }

    // CUSTOMER only
    @GetMapping("/my-accounts")
    public ResponseEntity<?> userAccounts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(accountService.userAccounts(user.getId()));
    }

    // CUSTOMER only
    @PutMapping("/deposit/{accountId}")
    public ResponseEntity<?> deposit(@PathVariable Integer accountId,
                                          @RequestParam Integer amount,
                                          @AuthenticationPrincipal User user) {
        accountService.deposit(accountId, amount, user.getId());
        return ResponseEntity.ok("Deposit successful");
    }

    // CUSTOMER only
    @PutMapping("/withdraw/{accountId}")
    public ResponseEntity<?> withdraw(@PathVariable Integer accountId,
                                           @RequestParam Integer amount,
                                           @AuthenticationPrincipal User user) {
        accountService.withdraw(accountId, amount, user.getId());
        return ResponseEntity.ok("Withdrawal successful");
    }

    // CUSTOMER only
    @PutMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestParam Integer senderAccountId,
                                           @RequestParam Integer receiverAccountId,
                                           @RequestParam Integer amount,
                                           @AuthenticationPrincipal User user) {
        accountService.transfer(senderAccountId, receiverAccountId, amount, user.getId());
        return ResponseEntity.ok("Transfer successful");
    }
}