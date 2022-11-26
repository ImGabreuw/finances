package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import org.springframework.stereotype.Component;

/**
 * Cálculo do <b>valor intrínseco</b> ou <b>preço justo</b> com base no método criado por <i>Benjamin Graham</i>.
 *
 * @see <a href="https://clubedovalor.com.br/blog/valor-justo-de-uma-acao/#:~:text=O%20c%C3%A1lculo%20do%20valor%20justo%20pensando%20por%20Benjamin%20Graham%20se,5%20x%20LPA%20x%20VPA">Clube do Valor</a>
 */
@Component
public class IntrinsicValueUseCase implements UseCase<IntrinsicValueUseCase.InputValues, IntrinsicValueUseCase.OutputValues> {

    private static final Double GRAHAM_CONSTANT = 22.5;

    @Override
    public OutputValues execute(InputValues input) {
        var LPA = input.LPA();
        var VPA = input.VPA();

        var intrinsicValue = Math.sqrt(GRAHAM_CONSTANT * LPA * VPA);

        return new OutputValues(
                NumberHelper.round(intrinsicValue)
        );
    }

    public record InputValues(Double LPA, Double VPA) implements UseCase.InputValues {
    }

    public record OutputValues(Double intrinsicValue) implements UseCase.OutputValues {
    }

}
