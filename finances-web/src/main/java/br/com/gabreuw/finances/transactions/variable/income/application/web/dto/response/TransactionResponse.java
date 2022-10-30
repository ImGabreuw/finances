package br.com.gabreuw.finances.transactions.variable.income.application.web.dto.response;

import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        String assetCode,
        AssetType assetType,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate operationDate,
        OperationType operationType,
        Double operationAverageCost,
        Integer numberOfShares,
        Double totalInvested
) implements Serializable {
}
