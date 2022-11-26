package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.InMemoryVariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class FindAllVariableIncomeTransactionByOperationDateUseCaseTest {

    private FindAllVariableIncomeTransactionByOperationDateUseCase underTest;

    @BeforeEach
    void setUp() {
        VariableIncomeTransactionRepository variableIncomeTransactionRepository = new InMemoryVariableIncomeTransactionRepository();
        underTest = new FindAllVariableIncomeTransactionByOperationDateUseCase(variableIncomeTransactionRepository);
    }

    @DisplayName("Should retrieve transaction with a specific operation date")
    @Test
    void shouldRetrieveTransactionWithASpecificOperationDate() {
        var operationDate = LocalDate.of(2022, 9, 2);
        var pageInfo = PageInfo.createDefault();
        var input = new FindAllVariableIncomeTransactionByOperationDateUseCase.InputValues(operationDate, pageInfo);

        var output = underTest.execute(input);

        assertThat(output.variableIncomeTransactions())
                .allMatch(transaction -> transaction.isOperationDateEqualsOrAfter(operationDate));
    }

}