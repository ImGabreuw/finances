package br.com.gabreuw.finances.announcement_search_engine.domain.errors;

import br.com.gabreuw.finances.shared.errors.BaseException;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.FAILED_DEPENDENCY;

public class MissingEnviromentVariableException extends BaseException {

    public MissingEnviromentVariableException(String enviromentVariable) {
        super(FAILED_DEPENDENCY, "Não foi possível encontrar a variável de ambiente com nome [%s]", enviromentVariable);
    }

}
