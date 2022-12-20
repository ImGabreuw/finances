package br.com.gabreuw.finances.investment_portfolio.domain.usecases;

import br.com.gabreuw.finances.investment_portfolio.domain.entities.VariableIncomeAsset;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.InMemoryVariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculateStocksPositionUseCaseTest {

    private CalculateStocksPositionUseCase underTest;

    @BeforeEach
    void setUp() {
        VariableIncomeTransactionRepository transactionRepository = new InMemoryVariableIncomeTransactionRepository();
        underTest = new CalculateStocksPositionUseCase(transactionRepository);
    }

    @DisplayName("Should calculate all assets position of the investment portfolio")
    @Test
    void shouldCalculateAllAssetsPositionOfTheInvestmentPortfolio() {
        var input = new CalculateStocksPositionUseCase.InputValues();

        var output = underTest.execute(input);

        assertThat(output.assets())
                .map(VariableIncomeAsset::getCode)
                .doesNotHaveDuplicates();
    }
}