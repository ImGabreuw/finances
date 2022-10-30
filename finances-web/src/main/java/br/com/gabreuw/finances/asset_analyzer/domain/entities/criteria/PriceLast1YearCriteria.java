package br.com.gabreuw.finances.asset_analyzer.domain.entities.criteria;

import br.com.gabreuw.finances.asset_analyzer.domain.entities.Criteria;

public class PriceLast1YearCriteria extends Criteria {

    private Double currentPrice;

    private Double minPriceLast1Year;

    private Double maxPriceLast1Year;

    public PriceLast1YearCriteria(Double currentPrice, Double minPriceLast1Year, Double maxPriceLast1Year) {
        super("current price comparing with last 12 months", currentPrice);
        this.currentPrice = currentPrice;
        this.minPriceLast1Year = minPriceLast1Year;
        this.maxPriceLast1Year = maxPriceLast1Year;
    }

    @Override
    public boolean isPositive() {
        return false;
    }

    @Override
    public boolean isNegative() {
        return false;
    }

}
