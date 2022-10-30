package br.com.gabreuw.finances.transactions.variable.income.domain.entities;

import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType;
import br.com.gabreuw.finances.utils.BlankSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FixedIncomeVariableIncomeTransactionTest {

    @DisplayName("Should create Transaction")
    @Test
    void shouldCreateTransaction() {
        var assetCode = "BBAS3";
        var assetType = AssetType.STOCK;
        var operationDate = LocalDate.of(2022, 8, 19);
        var operationType = OperationType.BUY;
        var operationAverageCost = 38.49;
        var numberOfShares = 20;

        var underTest = VariableIncomeTransaction.create(
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares
        );

        assertThat(underTest).isNotNull();
    }

    @DisplayName("Should throw Validation Exception when create Transaction because AssetCode is blank")
    @BlankSource
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateTransactionBecauseAssetCodeIsBlank(String assetCode) {
        var assetType = AssetType.STOCK;
        var operationDate = LocalDate.of(2022, 8, 19);
        var operationType = OperationType.BUY;
        var operationAverageCost = 38.49;
        var numberOfShares = 20;

        assertThatThrownBy(() -> VariableIncomeTransaction.create(
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares
        ))
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessage("The class Transaction have its constraints violated. [Field[fieldName=assetCode, message=must not be blank, value=%s]]".formatted(assetCode));
    }

    @DisplayName("Should throw ValidationException when create Transaction because AssetType is null")
    @NullSource
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateTransactionBecauseAssetTypeIsNull(AssetType assetType) {
        var assetCode = "BBAS3";
        var operationDate = LocalDate.of(2022, 8, 19);
        var operationType = OperationType.BUY;
        var operationAverageCost = 38.49;
        var numberOfShares = 20;

        assertThatThrownBy(() -> VariableIncomeTransaction.create(
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares
        ))
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessage("The class Transaction have its constraints violated. [Field[fieldName=assetType, message=must not be null, value=%s]]".formatted(assetType));
    }

    @DisplayName("Should throw ValidationException when create Transaction because OperationType is null")
    @NullSource
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateTransactionBecauseOperationTypeIsNull(OperationType operationType) {
        var assetCode = "BBAS3";
        var assetType = AssetType.STOCK;
        var operationDate = LocalDate.of(2022, 8, 19);
        var operationAverageCost = 38.49;
        var numberOfShares = 20;

        assertThatThrownBy(() -> VariableIncomeTransaction.create(
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares
        ))
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessage("The class VariableIncomeTransaction have its constraints violated. [Field[fieldName=operationType, message=must not be null, value=%s]]".formatted(operationType));
    }

    @DisplayName("Should throw ValidationException when create Transaction because OperationAverageCost is invalid")
    @ValueSource(doubles = {-1, 0, 1111.11, 1.111})
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateTransactionBecauseOperationAverageCostIsInvalid(Double operationAverageCost) {
        var assetCode = "BBAS3";
        var assetType = AssetType.STOCK;
        var operationDate = LocalDate.of(2022, 8, 19);
        var operationType = OperationType.BUY;
        var numberOfShares = 20;

        var exceptionMessage = assertThatThrownBy(() -> VariableIncomeTransaction.create(
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares
        ))
                .isInstanceOf(SelfValidation.ValidationException.class)
                .message();

        exceptionMessage.isIn(
                "The class VariableIncomeTransaction have its constraints violated. [Field[fieldName=operationAverageCost, message=must not be null, value=%s]]".formatted(operationAverageCost),
                "The class VariableIncomeTransaction have its constraints violated. [Field[fieldName=operationAverageCost, message=must be greater than 0, value=%s]]".formatted(operationAverageCost),
                "The class VariableIncomeTransaction have its constraints violated. [Field[fieldName=operationAverageCost, message=numeric value out of bounds (<3 digits>.<2 digits> expected), value=%s]]".formatted(operationAverageCost)
        );
    }

    @DisplayName("Should throw ValidationException when create Transaction because Shares is invalid")
    @ValueSource(ints = {-1, 0})
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateTransactionBecauseSharesIsInvalid(Integer numberOfShares) {
        var assetCode = "BBAS3";
        var assetType = AssetType.STOCK;
        var operationDate = LocalDate.of(2022, 8, 19);
        var operationType = OperationType.BUY;
        var operationAverageCost = 38.49;

        var exceptionMessage = assertThatThrownBy(() -> VariableIncomeTransaction.create(
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares
        ))
                .isInstanceOf(SelfValidation.ValidationException.class)
                .message();

        exceptionMessage.isIn(
                "The class Transaction have its constraints violated. [Field[fieldName=numberOfShares, message=must not be null, value=%s]]".formatted(numberOfShares),
                "The class Transaction have its constraints violated. [Field[fieldName=numberOfShares, message=must be greater than 0, value=%s]]".formatted(numberOfShares)
        );
    }

}