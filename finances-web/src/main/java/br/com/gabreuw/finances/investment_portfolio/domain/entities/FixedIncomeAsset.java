package br.com.gabreuw.finances.investment_portfolio.domain.entities;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Builder
@Data
public class FixedIncomeAsset implements SelfValidation<FixedIncomeAsset> {

    private Long id;

    @NotBlank
    private String code;

    @NotNull
    private AssetType assetType;

    @NotNull
    @Positive
    private Integer numberOfShares;

    @NotNull
    @Positive
    @Digits(integer = 3, fraction = 2)
    private Double averagePrice;

    public FixedIncomeAsset(Long id, String code, AssetType assetType, Integer numberOfShares, Double averagePrice) {
        this.id = id;
        this.code = code;
        this.assetType = assetType;
        this.numberOfShares = numberOfShares;
        this.averagePrice = NumberHelper.round(averagePrice);
        validate(this);
    }

    public Double getTotalInvested() {
        return averagePrice * numberOfShares;
    }

}
