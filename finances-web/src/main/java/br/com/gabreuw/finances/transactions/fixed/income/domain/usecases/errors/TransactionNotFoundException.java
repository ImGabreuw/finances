package br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.errors;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(Long id) {
        super("Transação não encontrada com ID (%s).".formatted(id));
    }

}
