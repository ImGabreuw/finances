package br.com.gabreuw.finances.bank_account.domain.entities;

import br.com.gabreuw.finances.bank_account.domain.entities.enums.BankAccountStatus;
import br.com.gabreuw.finances.shared.validation.SelfValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Builder
@Data
public class BankAccount implements SelfValidation<BankAccount> {

    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 3)
    private String bankCode;

    @NotNull
    @NotBlank
    @Length(min = 3, max = 5)
    private String agency;

    @NotNull
    @NotBlank
    private String accountNumber;

    @NotNull
    @NotBlank
    @Length(min = 1, max = 1)
    private String accountNumberCheckDigit;

    @NotNull
    private BankAccountStatus status;

    public BankAccount(Long id, String bankCode, String agency, String accountNumber, String accountNumberCheckDigit, BankAccountStatus status) {
        this.id = id;
        this.bankCode = bankCode;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.accountNumberCheckDigit = accountNumberCheckDigit;
        this.status = status == null ? BankAccountStatus.ACTIVE : status;
        validate(this);
    }

}
