package br.com.gabreuw.finances.bank_account.domain.entities;

import br.com.gabreuw.finances.bank_account.domain.entities.enums.BankAccountTransactionNature;
import br.com.gabreuw.finances.shared.validation.SelfValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class BankAccountTransaction implements SelfValidation<BankAccountTransaction> {

    private Long id;

    @NotNull
    private Long bankAccountId;

    @NotNull
    private LocalDate transactionDate;

    @NotNull
    private BankAccountTransactionNature nature;

    @NotNull
    @PositiveOrZero
    private Double amount;

    private String description;

    /**
     * Identificador da transação no banco, utilizado para evitar duplicidade de lançamentos e para realizar a conciliação.
     */
    @NotNull
    @NotBlank
    private String bankIdentifier;

    public BankAccountTransaction(Long id, Long bankAccountId, LocalDate transactionDate, BankAccountTransactionNature nature, Double amount, String description, String bankIdentifier) {
        this.id = id;
        this.bankAccountId = bankAccountId;
        this.transactionDate = transactionDate;
        this.nature = nature;
        this.amount = amount;
        this.description = description;
        this.bankIdentifier = bankIdentifier;
        validate(this);
    }

}
