package br.com.gabreuw.finances.transactions.fixed.income.application.config;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabreuw.finances.shared.errors.BaseExceptionResponse;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.errors.TransactionNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class TransactionExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<BaseExceptionResponse> handleTransactionNotFoundException(TransactionNotFoundException ex, HttpServletRequest request) {
        String path = request.getRequestURL().toString();

        BaseExceptionResponse response = BaseExceptionResponse.builder()
                .status(404)
                .error("Not Found")
                .message(ex.getMessage())
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(404).body(response);
    }

}
