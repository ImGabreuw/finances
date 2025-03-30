package br.com.gabreuw.finances.transactions.fixed.income.domain.entities;

import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType.DIRECT_TREASURE;
import static br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer.CDI;
import static br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer.IPCA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FixedIncomeVariableIncomeTransactionTest {

    @DisplayName("Should create Transaction of direct treasure")
    @Test
    void shouldCreateTransactionOfDirectTreasure() {
        var transaction = FixedIncomeTransaction.builder()
                .assetType(DIRECT_TREASURE)
                .description("Tesouro IPCA+ 2026")
                .profitabilityInPercent(6.24)
                .indexer(IPCA)
                .liquidity("diária")
                .expiresIn(LocalDate.of(2026, 8, 15))
                .totalInvested(4_996.12)
                .units(1.6)
                .operationDate(LocalDate.of(2022, 8, 15))
                .build();

        assertThat(transaction.getIssuingAgency()).isEqualTo("Tesouro Nacional");
    }

    @DisplayName("Should create Transaction of CDB without providing description")
    @Test
    void shouldCreateTransactionOfCdbWithoutProvidingDescription() {
        var assetType = AssetType.CDB;
        var issuingAgency = "Banco Master";

        var transaction = FixedIncomeTransaction.builder()
                .assetType(assetType)
                .issuingAgency(issuingAgency)
                .profitabilityInPercent(15.90)
                .indexer(CDI)
                .liquidity("no vencimento")
                .expiresIn(LocalDate.of(2027, 6, 22))
                .totalInvested(5_000.0)
                .units(5.0)
                .operationDate(LocalDate.of(2022, 8, 15))
                .build();

        assertThat(transaction.getDescription())
                .isEqualTo("%s - %s".formatted(assetType.getDescription(), issuingAgency));
    }

    @DisplayName("Should throw validation exception when building transaction with missing required fields")
    @Test
    void shouldThrowValidationExceptionWhenBuildingTransactionWithMissingRequiredFields() {
        assertThatThrownBy(() ->
                FixedIncomeTransaction.builder()
                        .description("Tesouro IPCA+ 2026")
                        .profitabilityInPercent(6.24)
                        .indexer(IPCA)
                        .liquidity("diária")
                        .expiresIn(LocalDate.of(2026, 8, 15))
                        .totalInvested(4_996.12)
                        .units(1.6)
                        .operationDate(LocalDate.of(2022, 8, 15))
                        .issuingAgency("Tesouro Nacional")
                        .build()
        )
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessageMatching("The class FixedIncomeTransaction have its constraints violated. \\[Field\\[fieldName=assetType, message=.*?, value=null]]");
    }

}