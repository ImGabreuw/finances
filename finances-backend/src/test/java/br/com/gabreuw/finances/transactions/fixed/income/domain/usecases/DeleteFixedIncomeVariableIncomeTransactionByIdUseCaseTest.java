package br.com.gabreuw.finances.transactions.fixed.income.domain.usecases;

import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.InMemoryFixedIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.FixedIncomeTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteFixedIncomeVariableIncomeTransactionByIdUseCaseTest {

    private DeleteFixedIncomeTransactionByIdUseCase underTest;
    private FixedIncomeTransactionRepository fixedIncomeTransactionRepository;

    @BeforeEach
    void setUp() {
        fixedIncomeTransactionRepository = new InMemoryFixedIncomeTransactionRepository();
        underTest = new DeleteFixedIncomeTransactionByIdUseCase(fixedIncomeTransactionRepository);
    }

    @DisplayName("Should delete transaction with ID 1")
    @Test
    void shouldDeleteTransactionWithId1() {
        var id = 1L;
        var input = new DeleteFixedIncomeTransactionByIdUseCase.InputValues(id);

        underTest.execute(input);

        assertThat(fixedIncomeTransactionRepository.existsById(id)).isFalse();
    }

}