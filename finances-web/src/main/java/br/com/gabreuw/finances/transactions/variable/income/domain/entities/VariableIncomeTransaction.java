package br.com.gabreuw.finances.transactions.variable.income.domain.entities;

import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class VariableIncomeTransaction implements SelfValidation<VariableIncomeTransaction> {

    private Long id;

    @NotBlank
    // TODO: 01/09/2022 Create annotation to validate asset code in B3 
    private String assetCode;

    @NotNull
    private AssetType assetType;

    // TODO: 04/09/2022 Create annotation to allow only working days
    private LocalDate operationDate;

    @NotNull
    private OperationType operationType;

    @NotNull
    @Positive
    @Digits(integer = 3, fraction = 2)
    private Double operationAverageCost;

    @NotNull
    @Positive
    private Integer numberOfShares;

    private Double totalInvested;

    public VariableIncomeTransaction(Long id, String assetCode, AssetType assetType, LocalDate operationDate, OperationType operationType, Double operationAverageCost, Integer numberOfShares, Double totalInvested) {
        this.id = id;
        this.assetCode = assetCode;
        this.assetType = assetType;
        this.operationDate = operationDate;
        this.operationType = operationType;
        this.operationAverageCost = operationAverageCost;
        this.numberOfShares = numberOfShares;
        this.totalInvested = totalInvested == null ? numberOfShares * operationAverageCost : totalInvested;

        validate(this);
    }

    public static VariableIncomeTransaction create(String assetCode, AssetType assetType, LocalDate operationDate, OperationType operationType, Double operationAverageCost, Integer numberOfShares) {
        return new VariableIncomeTransaction(
                null,
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares,
                null
        );
    }

    public boolean isOperationDateEqualsOrAfter(LocalDate operationDate) {
        return this.operationDate.isEqual(operationDate) || this.operationDate.isAfter(operationDate);
    }
}
