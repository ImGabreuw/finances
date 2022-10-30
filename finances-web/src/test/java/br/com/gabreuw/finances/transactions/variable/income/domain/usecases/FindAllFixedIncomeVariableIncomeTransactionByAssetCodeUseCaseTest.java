package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.InMemoryVariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FindAllFixedIncomeVariableIncomeTransactionByAssetCodeUseCaseTest {


    private FindAllVariableIncomeTransactionByAssetCodeUseCase underTest;

    @BeforeEach
    void setUp() {
        VariableIncomeTransactionRepository variableIncomeTransactionRepository = new InMemoryVariableIncomeTransactionRepository();
        underTest = new FindAllVariableIncomeTransactionByAssetCodeUseCase(variableIncomeTransactionRepository);
    }

    @DisplayName("Should retrieve all transaction with an specific asset code")
    @Test
    void shouldRetrieveAllTransactionWithAnSpecificAssetCode() {
        var assetCode = "TAEE11";
        var pageInfo = PageInfo.createDefault();
        var input = new FindAllVariableIncomeTransactionByAssetCodeUseCase.InputValues(assetCode, pageInfo);

        var output = underTest.execute(input);

        assertThat(output.variableIncomeTransactions())
                .map(VariableIncomeTransaction::getAssetCode)
                .containsOnly(assetCode);
    }

}