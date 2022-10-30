package br.com.gabreuw.finances.asset_analyzer.domain.service;

import br.com.gabreuw.finances.asset_analyzer.domain.usecases.AverageCeilingPriceUseCase;
import br.com.gabreuw.finances.asset_analyzer.domain.usecases.CurrentCeilingPriceUseCase;
import br.com.gabreuw.finances.asset_analyzer.domain.usecases.ProjectiveCeilingPriceUseCase;
import br.com.gabreuw.finances.shared.helper.NumberHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import static br.com.gabreuw.finances.asset_analyzer.domain.service.WeightedCeilingPriceService.Weights.*;

@RequiredArgsConstructor
public class WeightedCeilingPriceService {

    private final AverageCeilingPriceUseCase averageCeilingPriceUseCase;
    private final CurrentCeilingPriceUseCase currentCeilingPriceUseCase;
    private final ProjectiveCeilingPriceUseCase projectiveCeilingPriceUseCase;

    public Double calculate(List<Double> profitLast5Years, Integer numberOfShares, Double payoutLast5Years, Double DPALast12Months, Double firstQuarterProfit) {
        var averageCeilingPriceInputValues = new AverageCeilingPriceUseCase.InputValues(profitLast5Years, numberOfShares, payoutLast5Years);
        var currentCeilingPriceInputValues = new CurrentCeilingPriceUseCase.InputValues(DPALast12Months);
        var projectiveCeilingPriceInputValues = new ProjectiveCeilingPriceUseCase.InputValues(firstQuarterProfit, numberOfShares, payoutLast5Years);

        var averageCeilingPriceOutput = averageCeilingPriceUseCase.execute(averageCeilingPriceInputValues);
        var currentCeilingPriceOutput = currentCeilingPriceUseCase.execute(currentCeilingPriceInputValues);
        var projectiveCeilingPriceOutput = projectiveCeilingPriceUseCase.execute(projectiveCeilingPriceInputValues);

        var weightCeilingPrice = (averageCeilingPriceOutput.averageCeilingPrice() * AVERAGE_CEILING_PRICE.getValue() +
                currentCeilingPriceOutput.currentCeilingPrice() * CURRENT_CEILING_PRICE.getValue() +
                projectiveCeilingPriceOutput.conservativeProjectiveCeilingPrice() * CONSERVATIVE_PROJECTIVE_CEILING_PRICE.getValue() +
                projectiveCeilingPriceOutput.optimisticProjectiveCeilingPrice() * OPTIMISTIC_PROJECTIVE_CEILING_PRICE.getValue()) / Weights.sumWeights();

        return NumberHelper.round(weightCeilingPrice);
    }

    @RequiredArgsConstructor
    @Getter
    enum Weights {
        AVERAGE_CEILING_PRICE(3),
        CURRENT_CEILING_PRICE(2),
        CONSERVATIVE_PROJECTIVE_CEILING_PRICE(2),
        OPTIMISTIC_PROJECTIVE_CEILING_PRICE(1);


        private final int value;

        public static int sumWeights() {
            return Arrays.stream(Weights.values())
                    .map(Weights::getValue)
                    .reduce(0, Integer::sum);
        }
    }

}
