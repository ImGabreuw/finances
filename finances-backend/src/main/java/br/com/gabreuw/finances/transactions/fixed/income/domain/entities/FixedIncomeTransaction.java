package br.com.gabreuw.finances.transactions.fixed.income.domain.entities;

import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import static br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType.DIRECT_TREASURE;

@Builder
@Data
public class FixedIncomeTransaction implements SelfValidation<FixedIncomeTransaction> {

    private Long id;

    @NotNull
    private AssetType assetType;

    @NotBlank
    private String issuingAgency;

    @NotBlank
    private String description;

    @NotNull
    @Digits(integer = 2, fraction = 2)
    @Positive
    private Double profitabilityInPercent;

    @NotNull
    private Indexer indexer;

    @NotBlank
    private String liquidity;

    // TODO: 02/10/2022 Create annotation to validade if the "expiresIn" is future date
    @NotNull
    private LocalDate expiresIn;

    @NotNull
    private Double totalInvested;

    @NotNull
    @Digits(integer = 5, fraction = 2)
    @Positive
    private Double units;

    @NotNull
    private LocalDate operationDate;

    public FixedIncomeTransaction(Long id, AssetType assetType, String issuingAgency, String description, Double profitabilityInPercent, Indexer indexer, String liquidity, LocalDate expiresIn, Double totalInvested, Double units, LocalDate operationDate) {
        this.id = id;
        this.assetType = assetType;
        this.issuingAgency = issuingAgency;
        this.description = description == null ? assetType.getDescription() + " - " + issuingAgency : description;
        this.profitabilityInPercent = profitabilityInPercent;
        this.indexer = indexer;
        this.liquidity = liquidity;
        this.expiresIn = expiresIn;
        this.totalInvested = totalInvested;
        this.units = units;
        this.operationDate = operationDate;

        if (assetType == DIRECT_TREASURE) {
            this.issuingAgency = "Tesouro Nacional";
        }

        validate(this);
    }

}
