package br.com.gabreuw.finances.transactions.variable.income.application.mapper;

import br.com.gabreuw.finances.transactions.variable.income.application.database.entities.VariableIncomeTransactionEntity;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.mapper.VariableIncomeTransactionMapper;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class FixedIncomeFixedIncomeFixedIncomeVariableIncomeVariableIncomeTransactionMapperImplTest {

    private VariableIncomeTransactionMapper underTest;

    @BeforeEach
    void setUp() {
        underTest = new VariableIncomeTransactionMapperImpl();
    }

    @DisplayName("Should convert Transaction to TransactionEntity")
    @Test
    void shouldConvertTransactionToTransactionEntity() {
        var id = 1L;
        var assetCode = "BBAS3";
        var assetType = AssetType.STOCK;
        var operationDate = LocalDate.of(2022, 8, 19);
        var operationType = OperationType.BUY;
        var operationAverageCost = 38.49;
        var numberOfShares = 20;

        var transaction = new VariableIncomeTransaction(
                id,
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares,
                null
        );

        var transactionEntity = underTest.mapToEntity(transaction);

        assertThat(transactionEntity).isInstanceOf(VariableIncomeTransactionEntity.class);
        assertThat(transactionEntity).hasNoNullFieldsOrProperties();
    }

    @DisplayName("Should covert TransactionEntity to Transaction")
    @Test
    void shouldCovertTransactionEntityToTransaction() {
        var id = 1L;
        var assetCode = "BBAS3";
        var assetType = AssetType.STOCK;
        var operationDate = LocalDate.of(2022, 8, 19);
        var operationType = OperationType.BUY;
        var operationAverageCost = 38.49;
        var numberOfShares = 20;
        var totalInvested = operationAverageCost * numberOfShares;

        var transactionEntity = new VariableIncomeTransactionEntity(
                id,
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares,
                totalInvested
        );

        var transaction = underTest.mapToDomain(transactionEntity);

        assertThat(transaction).isInstanceOf(VariableIncomeTransaction.class);
        assertThat(transaction).hasNoNullFieldsOrProperties();
    }

}