package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Cálculo do <b>preço teto médio</b> com base no método criado por <i>Décio Bazin</i>.
 *
 * @see <a href="https://miraiinvesting.com/como-calcular-o-preco-teto-de-acao-metodo-barsi/#:~:text=Por%20outro%20lado%2C%20o%20pre%C3%A7o,ele%20espera%20receber%20daquele%20ativo.">Mirai Investing</a>
 */
@Component
public class AverageCeilingPriceUseCase implements UseCase<AverageCeilingPriceUseCase.InputValues, AverageCeilingPriceUseCase.OutputValues> {

    @Override
    public OutputValues execute(InputValues input) {
        var profitLast5Years = input.profitLast5Years();
        var numberOfShares = input.numberOfShares();
        var payoutLast5Years = input.payoutLast5Years();

        var sumOfProfits = profitLast5Years.stream().reduce(0.0, Double::sum);
        var averageAnnualProfit = sumOfProfits / profitLast5Years.size();

        var averageLPA = averageAnnualProfit / numberOfShares;

        var averageDPA = (payoutLast5Years / 100) * averageLPA;

        var averageCeilingPrice = averageDPA / 0.06;

        return new OutputValues(
                NumberHelper.round(averageCeilingPrice)
        );
    }

    public record InputValues(
            List<Double> profitLast5Years,
            Integer numberOfShares,
            Double payoutLast5Years
    ) implements UseCase.InputValues {
    }

    public record OutputValues(Double averageCeilingPrice) implements UseCase.OutputValues {
    }

}
