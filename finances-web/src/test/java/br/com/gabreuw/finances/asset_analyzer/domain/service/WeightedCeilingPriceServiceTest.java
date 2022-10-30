package br.com.gabreuw.finances.asset_analyzer.domain.service;

import br.com.gabreuw.finances.asset_analyzer.domain.usecases.AverageCeilingPriceUseCase;
import br.com.gabreuw.finances.asset_analyzer.domain.usecases.CurrentCeilingPriceUseCase;
import br.com.gabreuw.finances.asset_analyzer.domain.usecases.ProjectiveCeilingPriceUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class WeightedCeilingPriceServiceTest {
    
    private WeightedCeilingPriceService underTest;

    @BeforeEach
    void setUp() {
        var averageCeilingPriceUseCase = new AverageCeilingPriceUseCase();
        var currentCeilingPriceUseCase = new CurrentCeilingPriceUseCase();
        var projectiveCeilingPriceUseCase = new ProjectiveCeilingPriceUseCase();

        underTest = new WeightedCeilingPriceService(averageCeilingPriceUseCase, currentCeilingPriceUseCase, projectiveCeilingPriceUseCase);
    }

    @DisplayName("Should calculate ceiling price")
    @Test
    void shouldCalculateCeilingPrice() {
        List<Double> profitLast5Years = List.of(2_213_710_000.0, 2_262_930_000.0, 1_002_320_000.0, 1_071_310_000.0, 648_290_000.0);
        var numberOfShares = 1_033_496_721;
        var payoutLast5Years = 67.98;
        var DPALast12Months = 5.3121;
        var firstQuarterProfit = 559_920_000.0;

        var weightedCeilingPrice = underTest.calculate(profitLast5Years, numberOfShares, payoutLast5Years, DPALast12Months, firstQuarterProfit);

        System.out.println(weightedCeilingPrice);
    }
    
}