package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import org.springframework.stereotype.Component;

/**
 * Cálculo do <b>preço teto atual</b> com base no método criado por <i>Décio Bazin</i>.
 *
 * @see <a href="https://miraiinvesting.com/como-calcular-o-preco-teto-de-acao-metodo-barsi/#:~:text=Por%20outro%20lado%2C%20o%20pre%C3%A7o,ele%20espera%20receber%20daquele%20ativo.">Mirai Investing</a>
 */
@Component
public class CurrentCeilingPriceUseCase implements UseCase<CurrentCeilingPriceUseCase.InputValues, CurrentCeilingPriceUseCase.OutputValues> {

    @Override
    public OutputValues execute(InputValues input) {
        var DPALast12Months = input.DPALast12Months();

        var currentCeilingPrice = DPALast12Months / 0.06;

        return new OutputValues(
                NumberHelper.round(currentCeilingPrice)
        );
    }

    public record InputValues(Double DPALast12Months) implements UseCase.InputValues {
    }

    public record OutputValues(Double currentCeilingPrice) implements UseCase.OutputValues {
    }

}
