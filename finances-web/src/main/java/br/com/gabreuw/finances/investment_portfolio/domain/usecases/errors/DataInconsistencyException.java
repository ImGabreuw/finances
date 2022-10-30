package br.com.gabreuw.finances.investment_portfolio.domain.usecases.errors;

public class DataInconsistencyException extends RuntimeException {

    public DataInconsistencyException(String message, Object... values) {
        super(message.formatted(values));
    }

}
