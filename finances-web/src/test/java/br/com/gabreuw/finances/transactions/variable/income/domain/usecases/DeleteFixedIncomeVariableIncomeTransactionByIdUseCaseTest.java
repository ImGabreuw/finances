package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.InMemoryVariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteFixedIncomeVariableIncomeTransactionByIdUseCaseTest {

    private VariableIncomeTransactionRepository variableIncomeTransactionRepository;
    private DeleteVariableIncomeTransactionByIdUseCase underTest;

    @BeforeEach
    void setUp() {
        variableIncomeTransactionRepository = new InMemoryVariableIncomeTransactionRepository();
        underTest = new DeleteVariableIncomeTransactionByIdUseCase(variableIncomeTransactionRepository);
    }

    @DisplayName("Should delete transaction by id")
    @Test
    void shouldDeleteTransactionById() {
        var id = 1L;
        var input = new DeleteVariableIncomeTransactionByIdUseCase.InputValues(id);

        underTest.execute(input);

        assertThat(variableIncomeTransactionRepository.existsById(id)).isFalse();
    }
}