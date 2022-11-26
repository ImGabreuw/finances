package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Cálculo do <b>preço teto projetivo</b> com base no método criado por <i>Décio Bazin</i>.
 *
 * @see <a href="https://miraiinvesting.com/como-calcular-o-preco-teto-de-acao-metodo-barsi/#:~:text=Por%20outro%20lado%2C%20o%20pre%C3%A7o,ele%20espera%20receber%20daquele%20ativo.">Mirai Investing</a>
 */
@Component
public class ProjectiveCeilingPriceUseCase implements UseCase<ProjectiveCeilingPriceUseCase.InputValues, ProjectiveCeilingPriceUseCase.OutPutValues> {

    @Override
    public OutPutValues execute(InputValues input) {
        var optimisticProjectiveCeilingPrice = calculateProjectiveCeilingPrice(Projection.OPTIMISTIC, input);
        var conservativeProjectiveCeilingPrice = calculateProjectiveCeilingPrice(Projection.CONSERVATIVE, input);

        return new OutPutValues(
                NumberHelper.round(optimisticProjectiveCeilingPrice),
                NumberHelper.round(conservativeProjectiveCeilingPrice)
        );
    }

    private Double calculateProjectiveCeilingPrice(Projection projection, InputValues input) {
        var firstQuarterProfit = input.firstQuarterProfit();
        var numberOfShares = input.numberOfShares();
        var averagePayout = input.payoutLast5Years();

        var projectiveLPA = (firstQuarterProfit * projection.getReplicateQuarterlyResult()) / numberOfShares;
        var projectiveDPA = projectiveLPA * (averagePayout / 100);

        return projectiveDPA / 0.06;
    }

    public record InputValues(
            Double firstQuarterProfit,
            Integer numberOfShares,
            Double payoutLast5Years
    ) implements UseCase.InputValues {
    }

    public record OutPutValues(
            Double optimisticProjectiveCeilingPrice,
            Double conservativeProjectiveCeilingPrice
    ) implements UseCase.OutputValues {
    }

    @RequiredArgsConstructor
    @Getter
    private enum Projection {
        OPTIMISTIC(4),
        CONSERVATIVE(3);

        private final int replicateQuarterlyResult;
    }

}
