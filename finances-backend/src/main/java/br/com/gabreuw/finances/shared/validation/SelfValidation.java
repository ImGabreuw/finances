package br.com.gabreuw.finances.shared.validation;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Validar a entidade que implementa a interface {@link SelfValidation}, e em caso de alguma constraint for violada, é lançada uma exceção com o(s) campo(s) violado(s) e suas respectivas mensagens de erro
 *
 * @param <T> A classe da entidade implementada por esta ‘interface’
 * @author ImGabreuw
 */
public interface SelfValidation<T> {

    Logger LOG = LoggerFactory.getLogger(SelfValidation.class);

    private static Validator getValidator() {
        try (var validator = Validation.buildDefaultValidatorFactory()) {
            return validator.getValidator();
        } catch (ValidationException exception) {
            var message = "Error to instantiate validator: " + exception.getMessage();
            LOG.error(message);
            throw new IllegalStateException(message);
        }
    }

    default void validate(T entity) {
        Set<ConstraintViolation<T>> constraintViolations = getValidator().validate(entity);

        if (constraintViolations.isEmpty()) return;

        throw new ValidationException(
                entity,
                constraintViolations.stream()
                        .map(this::binding)
                        .toList()
        );
    }

    private Field binding(ConstraintViolation<T> violation) {
        return new Field(
                violation.getPropertyPath().toString(),
                violation.getMessage(),
                violation.getInvalidValue()
        );
    }

    record Field(String fieldName, String message, Object value) {
    }

    class ValidationException extends RuntimeException {

        @Getter
        private final List<Field> violatedFields = new ArrayList<>();

        public ValidationException(Object target, List<Field> violatedFields) {
            super(String.format(
                    "The class %s have its constraints violated. %s",
                    target.getClass().getSimpleName(),
                    violatedFields
            ));
        }

    }

}
