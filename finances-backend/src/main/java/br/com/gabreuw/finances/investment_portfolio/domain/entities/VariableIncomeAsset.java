package br.com.gabreuw.finances.investment_portfolio.domain.entities;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
public class VariableIncomeAsset implements SelfValidation<VariableIncomeAsset> {

    private Long id;

    @NotBlank
    private String code;

    @NotNull
    private AssetType type;

    @NotNull
    @Positive
    private Integer numberOfShares;

    @NotNull
    @Positive
    @Digits(integer = 3, fraction = 2)
    private Double averagePrice;

    public VariableIncomeAsset(Long id, String code, AssetType type, Integer numberOfShares, Double averagePrice) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.numberOfShares = numberOfShares;
        this.averagePrice = NumberHelper.round(averagePrice);
        validate(this);
    }

    public Double getTotalInvested() {
        return averagePrice * numberOfShares;
    }

}
