package br.com.gabreuw.finances.bank_account.domain.entities.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BankAccountTransactionNature {

    INCOME("INCOME"),
    EXPENSE("EXPENSE");

    private final String value;

    public static BankAccountTransactionNature fromValue(String value) {
        for (BankAccountTransactionNature nature : values()) {
            if (nature.getValue().equals(value)) {
                return nature;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }

}
