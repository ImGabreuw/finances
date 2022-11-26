package br.com.gabreuw.finances.transactions.fixed.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.InMemoryFixedIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.FixedIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FindAllFixedIncomeVariableIncomeTransactionUseCaseTest {

    private FindAllFixedIncomeTransactionUseCase underTest;

    @BeforeEach
    void setUp() {
        FixedIncomeTransactionRepository fixedIncomeTransactionRepository = new InMemoryFixedIncomeTransactionRepository();
        underTest = new FindAllFixedIncomeTransactionUseCase(fixedIncomeTransactionRepository);
    }

    @DisplayName("Should retrieve 20 transaction from database")
    @Test
    void shouldRetrieve20TransactionFromDatabase() {
        var pageInfo = PageInfo.createDefault();
        var input = new FindAllFixedIncomeTransactionUseCase.InputValues(pageInfo);

        var output = underTest.execute(input);

        assertThat(output.fixedIncomeTransactions()).hasSize(pageInfo.getPageSize());
        assertThat(output.fixedIncomeTransactions())
                .map(FixedIncomeTransaction::getId)
                .doesNotHaveDuplicates();
    }

}