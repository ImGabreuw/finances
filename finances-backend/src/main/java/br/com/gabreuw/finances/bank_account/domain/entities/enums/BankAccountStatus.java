package br.com.gabreuw.finances.bank_account.domain.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BankAccountStatus {

    ACTIVE("Ativo"),
    INACTIVE("Inativo"),
    CLOSED("Fechado");

    private final String description;

    public static BankAccountStatus from(String status) {
        for (BankAccountStatus bankAccountStatus : BankAccountStatus.values()) {
            if (bankAccountStatus.getDescription().equalsIgnoreCase(status)) {
                return bankAccountStatus;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + status);
    }

}
