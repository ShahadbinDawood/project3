package com.example.bank_system.Dto.In;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTOIn {
    @NotNull(message = "customer Id can not be empty")
    private Integer customerId;
    @NotEmpty(message = "account Number can not be empty")
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "Account number must match the format XXXX-XXXX-XXXX-XXXX")
    private  String accountNumber;
    @PositiveOrZero
    @NotNull(message = "balance can not be empty")
    private Integer balance;


}
