package br.com.gabreuw.finances.transactions.fixed.income.application.web.dto.request;

import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.time.LocalDate;

public record SaveTransactionRequest(
        @NotNull
        AssetType assetType,

        String issuingAgency,

        String description,

        @NotNull
        @Digits(integer = 2, fraction = 2)
        @Positive
        Double profitabilityInPercent,

        @NotNull
        Indexer indexer,

        @NotBlank
        String liquidity,

        @NotNull
        LocalDate expiresIn,

        @NotNull
        Double totalInvested,

        @NotNull
        @Digits(integer = 5, fraction = 2)
        @Positive
        Double units,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate operationDate
) implements Serializable {
}
