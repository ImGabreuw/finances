package br.com.gabreuw.finances.shared.errors;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    @Getter
    private final HttpStatus httpStatus;

    public BaseException(HttpStatus httpStatus, String message, Object... values) {
        super(message.formatted(values));
        this.httpStatus = httpStatus;
    }

    public int getStatusCode() {
        return httpStatus.value();
    }

}
