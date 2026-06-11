package com.example.bank_system.Dto.Out;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTOOut {
    private Integer id;
    private String accountNumber;
    private Integer balance;
    private boolean isActive;
    private Integer customerId;


}
