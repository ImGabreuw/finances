package br.com.gabreuw.finances.transactions.fixed.income.application.web.dto.response;

import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
public record TransactionResponse(
        Long id,
        AssetType assetType,
        String issuingAgency,
        String description,
        Double profitabilityInPercent,
        Indexer indexer,
        String liquidity,
        LocalDate expiresIn,
        Double totalInvested,
        Double units,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate operationDate
) implements Serializable {
}
