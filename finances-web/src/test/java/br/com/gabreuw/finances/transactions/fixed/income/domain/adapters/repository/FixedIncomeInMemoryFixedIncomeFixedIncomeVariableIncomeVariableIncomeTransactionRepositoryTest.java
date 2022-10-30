package br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType.CDB;
import static br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer.CDI;
import static org.assertj.core.api.Assertions.assertThat;

class FixedIncomeInMemoryFixedIncomeFixedIncomeVariableIncomeVariableIncomeTransactionRepositoryTest {

    private FixedIncomeTransactionRepository underTest;

    @BeforeEach
    void setUp() {
        underTest = new InMemoryFixedIncomeTransactionRepository();
    }

    @DisplayName("Should check if all saved transactions has different ID")
    @Test
    void shouldCheckIfAllSavedTransactionsHasDifferentId() {
        var pageInfo = PageInfo.createDefault();

        var transactions = underTest.findAll(pageInfo);

        assertThat(transactions)
                .flatMap(FixedIncomeTransaction::getId)
                .doesNotHaveDuplicates();
    }

    @DisplayName("Should save transaction")
    @Test
    void shouldSaveTransaction() {
        var transaction = FixedIncomeTransaction.builder()
                .assetType(CDB)
                .issuingAgency("Banco Master")
                .profitabilityInPercent(15.90)
                .indexer(CDI)
                .liquidity("no vencimento")
                .expiresIn(LocalDate.of(2027, 6, 22))
                .totalInvested(5_000.0)
                .units(5.0)
                .operationDate(LocalDate.of(2022, 8, 15))
                .build();

        var savedTransaction = underTest.save(transaction);

        assertThat(savedTransaction).isNotNull();
        assertThat(savedTransaction.getId()).isEqualTo(PageInfo.createDefault().getPageSize() + 1L);
    }

}