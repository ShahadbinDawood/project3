package com.example.bank_system.Repository;

import com.example.bank_system.Model.Account;
import com.example.bank_system.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AccountRepository  extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);
    Set<Account> findAccountByCustomerId(Integer customerId);
    Account findAccountByAccountNumber(String accountNumber);
}
