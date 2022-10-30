package br.com.gabreuw.finances.transactions.variable.income.application.web.dto.request;

import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;

public record SaveTransactionRequest(
        @NotBlank
        String assetCode,

        @NotNull
        AssetType assetType,

        @JsonFormat(pattern = "yyyy-MM-dd")
        @NotNull
        LocalDate operationDate,

        @NotNull
        OperationType operationType,

        @NotNull
        @Positive
        Double operationAverageCost,

        @NotNull
        @Positive
        Integer numberOfShares
) implements Serializable {
}
