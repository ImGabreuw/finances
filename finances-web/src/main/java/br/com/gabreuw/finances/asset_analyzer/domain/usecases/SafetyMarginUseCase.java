package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class SafetyMarginUseCase implements UseCase<SafetyMarginUseCase.InputValues, SafetyMarginUseCase.OutputValues> {

    @Override
    public OutputValues execute(InputValues input) {
        var intrinsicValue = input.intrinsicValue();
        var stockPrice = input.stockPrice();

        var safetyMarginInPercentage = ((intrinsicValue / stockPrice) - 1) * 100;

        return new OutputValues(
                NumberHelper.round(safetyMarginInPercentage)
        );
    }

    public record InputValues(Double intrinsicValue, Double stockPrice) implements UseCase.InputValues {
    }

    public record OutputValues(Double safetyMarginInPercentage) implements UseCase.OutputValues {
    }

}
