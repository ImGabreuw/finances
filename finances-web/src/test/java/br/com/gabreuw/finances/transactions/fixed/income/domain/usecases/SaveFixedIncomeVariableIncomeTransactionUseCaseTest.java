package br.com.gabreuw.finances.transactions.fixed.income.domain.usecases;

import br.com.gabreuw.finances.shared.helper.FixedIncomeTransactionHelper;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.InMemoryFixedIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.FixedIncomeTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SaveFixedIncomeVariableIncomeTransactionUseCaseTest {

    private SaveFixedIncomeTransactionUseCase underTest;

    @BeforeEach
    void setUp() {
        FixedIncomeTransactionRepository fixedIncomeTransactionRepository = new InMemoryFixedIncomeTransactionRepository();
        underTest = new SaveFixedIncomeTransactionUseCase(fixedIncomeTransactionRepository);
    }

    @DisplayName("Should save transaction")
    @Test
    void shouldSaveTransaction() {
        var fakeTransaction = FixedIncomeTransactionHelper.getINSTANCE().createFakeTransaction();
        var input = new SaveFixedIncomeTransactionUseCase.InputValues(
                fakeTransaction.getAssetType(),
                fakeTransaction.getIssuingAgency(),
                fakeTransaction.getDescription(),
                fakeTransaction.getProfitabilityInPercent(),
                fakeTransaction.getIndexer(),
                fakeTransaction.getLiquidity(),
                fakeTransaction.getExpiresIn(),
                fakeTransaction.getTotalInvested(),
                fakeTransaction.getUnits(),
                fakeTransaction.getOperationDate()
        );

        var output = underTest.execute(input);

        assertThat(output.fixedIncomeTransaction().getId()).isNotNull();
    }

}