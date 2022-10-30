package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.InMemoryVariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FindAllFixedIncomeVariableIncomeTransactionUseCaseTest {

    private FindAllVariableIncomeTransactionUseCase underTest;

    @BeforeEach
    void setUp() {
        VariableIncomeTransactionRepository variableIncomeTransactionRepository = new InMemoryVariableIncomeTransactionRepository();
        underTest = new FindAllVariableIncomeTransactionUseCase(variableIncomeTransactionRepository);
    }

    @DisplayName("Should retrieve 20 transaction")
    @Test
    void shouldRetrieve10Transaction() {
        var pageInfo = PageInfo.createDefault();

        var input = new FindAllVariableIncomeTransactionUseCase.InputValues(pageInfo);

        var output = underTest.execute(input);

        assertThat(output.variableIncomeTransactions()).hasSize(pageInfo.getPageSize());
    }

}