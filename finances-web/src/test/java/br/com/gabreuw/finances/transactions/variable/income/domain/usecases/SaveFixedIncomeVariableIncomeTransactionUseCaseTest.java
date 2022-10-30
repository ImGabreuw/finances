package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.InMemoryVariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SaveFixedIncomeVariableIncomeTransactionUseCaseTest {

    private SaveVariableIncomeTransactionUseCase underTest;

    @BeforeEach
    void setUp() {
        VariableIncomeTransactionRepository variableIncomeTransactionRepository = new InMemoryVariableIncomeTransactionRepository();
        underTest = new SaveVariableIncomeTransactionUseCase(variableIncomeTransactionRepository);
    }

    @DisplayName("Should save transaction")
    @Test
    void shouldSaveTransaction() {
        var input = new SaveVariableIncomeTransactionUseCase.InputValues(
                "TAEE11",
                AssetType.STOCK,
                LocalDate.of(2022, 9, 2),
                OperationType.BUY,
                42.12,
                10
        );

        var output = underTest.execute(input);

        assertThat(output.variableIncomeTransaction().getId()).isNotNull();
    }
}