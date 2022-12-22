package br.com.gabreuw.finances.investment_portfolio.domain.entities;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import jakarta.validation.constraints.*;
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

    @NotNull
    @Positive
    @Digits(integer = 3, fraction = 2)
    private Double currentPrice;

    @NotNull
    @PositiveOrZero
    private Integer securityMarginInPercente;

    @NotNull
    @Positive
    @Digits(integer = 3, fraction = 2)
    private Double cielingPrice;

    public VariableIncomeAsset(Long id, String code, AssetType type, Integer numberOfShares, Double averagePrice, Double currentPrice, Integer securityMarginInPercente, Double cielingPrice) {
        this.id = id;
        this.code = code;
        this.type = type;
        this.numberOfShares = numberOfShares;
        this.averagePrice = NumberHelper.round(averagePrice);
        this.currentPrice = NumberHelper.round(currentPrice);
        this.securityMarginInPercente = securityMarginInPercente;
        this.cielingPrice = NumberHelper.round(cielingPrice);
        validate(this);
    }

    public Double getTotalInvested() {
        return averagePrice * numberOfShares;
    }

    public Double getWorthPriceToBuy() {
        return NumberHelper.round(cielingPrice * (securityMarginInPercente / 100));
    }

    public boolean isWorthToBuy() {
        return currentPrice <= getWorthPriceToBuy();
    }

}
