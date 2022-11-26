package br.com.gabreuw.finances.transactions.variable.income.domain.usecases.exceptions;

public class VariableIncomeTransactionException extends RuntimeException {

    public VariableIncomeTransactionException(String message) {
        super(message);
    }

    public VariableIncomeTransactionException(String message, Object... values) {
        super(message.formatted(values));
    }

}
