package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.InMemoryVariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FindAllFixedIncomeVariableIncomeTransactionByAssetTypeUseCaseTest {

    private FindAllVariableIncomeTransactionByAssetTypeUseCase underTest;

    @BeforeEach
    void setUp() {
        VariableIncomeTransactionRepository variableIncomeTransactionRepository = new InMemoryVariableIncomeTransactionRepository();
        underTest = new FindAllVariableIncomeTransactionByAssetTypeUseCase(variableIncomeTransactionRepository);
    }

    @DisplayName("Should retrieve transactions with a specific asset type")
    @Test
    void shouldRetrieveTransactionsWithASpecificAssetType() {
        var assetType = "STOCK";
        var pageInfo = PageInfo.createDefault();
        var input = new FindAllVariableIncomeTransactionByAssetTypeUseCase.InputValues(assetType, pageInfo);

        var output = underTest.execute(input);

        assertThat(output.variableIncomeTransactions())
                .map(VariableIncomeTransaction::getAssetType)
                .containsOnly(AssetType.valueOf(assetType));
    }

}