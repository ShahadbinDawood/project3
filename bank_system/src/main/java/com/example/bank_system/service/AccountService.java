package com.example.bank_system.service;

import com.example.bank_system.Api.ApiException;
import com.example.bank_system.Dto.In.AccountDTOIn;
import com.example.bank_system.Dto.Out.AccountDTOOut;
import com.example.bank_system.Model.Account;
import com.example.bank_system.Model.Customer;
import com.example.bank_system.Model.User;
import com.example.bank_system.Repository.AccountRepository;
import com.example.bank_system.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public List<AccountDTOOut> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(a -> modelMapper.map(a, AccountDTOOut.class)).collect(Collectors.toList());
    }
    public void updateAccount(Integer accountId, AccountDTOIn accountIn, User user) {
        Customer customer = customerRepository.findCustomersByUser_Id(user.getId());
        if (customer == null) throw new ApiException("Customer not found");
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(customer.getId()))
            throw new ApiException("Unauthorized");
        account.setAccountNumber(accountIn.getAccountNumber());
        accountRepository.save(account);
    }

    public void deleteAccount(Integer accountId, User user) {
        Customer customer = customerRepository.findCustomersByUser_Id(user.getId());
        if (customer == null) throw new ApiException("Customer not found");
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) throw new ApiException("Account not found");
        if (!account.getCustomer().getId().equals(customer.getId()))
            throw new ApiException("Unauthorized");
        accountRepository.delete(account);
    }
    public  AccountDTOOut viewAccountDetails (Integer id ){
        Account account = accountRepository.findAccountById(id);
        if (account==null)throw new ApiException("Account not found");
        return modelMapper.map(account, AccountDTOOut.class) ;
    }
    public  void  CreateAccount (AccountDTOIn accountIn){
        Customer customer =customerRepository.findCustomerById(accountIn.getCustomerId());
        if (customer == null) throw new ApiException("Customer not found");
       Account account = new Account(); 
       account.setAccountNumber(accountIn.getAccountNumber());
       account.setBalance(accountIn.getBalance());
        account.setCustomer(customer);
        account.setActive(false);
        accountRepository.save(account);
    }
    public List<AccountDTOOut>  userAccounts (Integer userId ){
        Customer customer =customerRepository.findCustomerById(userId);
        if (customer == null) throw new ApiException("Customer not found");
        Set<Account> accounts = customer.getAccounts();
        if (accounts == null) throw new ApiException("Account not found");
        return accounts.stream().map(a -> modelMapper.map(a, AccountDTOOut.class)).collect(Collectors.toList());
    }
    public void activateAccount (Integer id ){
        Account account = accountRepository.findAccountById(id);
        if (account==null)throw new ApiException("Account not found");
        account.setActive(true);
        accountRepository.save(account);
    }
    public void deposit(Integer id , Integer amount ,Integer userId){
        Customer customer =customerRepository.findCustomersByUser_Id(userId);
        if (customer == null) throw new ApiException("Customer not found");
        Account account = accountRepository.findAccountById(id);
        if (account==null)throw new ApiException("Account not found");
        if (!account.isActive())throw new ApiException("Account not Active");
        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new ApiException("Unauthorized");
        }
        if (amount<=0)throw new ApiException("amount  should be greater than 0");
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }
    public void withdraw(Integer id , Integer amount,Integer userId ){
        Customer customer =customerRepository.findCustomersByUser_Id(userId);
        if (customer == null) throw new ApiException("Customer not found");
        Account account = accountRepository.findAccountById(id);
        if (account==null)throw new ApiException("Account not found");
        if (!account.isActive())throw new ApiException("Account not Active");
        if (!account.getCustomer().getId().equals(customer.getId())) {
            throw new ApiException("Unauthorized");
        }
        if (account.getBalance()<amount)throw new ApiException("Insufficient balance");
        if (amount<=0)throw new ApiException("amount  should be greater than 0");
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }
    public  void transfer(Integer senderId , Integer resaverId, Integer amount ,Integer userId){
        Customer customer =customerRepository.findCustomersByUser_Id(userId);
        if (customer == null) throw new ApiException("Customer not found");
        Account senderAccount = accountRepository.findAccountById(senderId);
        Account receiverAccount = accountRepository.findAccountById(resaverId);
        if (senderAccount == null || receiverAccount == null)
            throw new ApiException("Account not found");
        if (!senderAccount.isActive() || !receiverAccount.isActive())
            throw new ApiException("One or both accounts are not active");
        if (!senderAccount.getCustomer().getId().equals(customer.getId()))
            throw new ApiException("Unauthorized");
        if (amount <= 0) throw new ApiException("Amount should be greater than 0");
        if (senderAccount.getBalance() < amount)
            throw new ApiException("Insufficient balance");
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
    }
    public void blockAccount(Integer id) {
        Account account = accountRepository.findAccountById(id);
        if (account == null) throw new ApiException("Account not found");
        account.setActive(false);
        accountRepository.save(account);
    }



}
