package br.com.gabreuw.finances.shared.errors;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BaseExceptionResponse {

    private int status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;

}
