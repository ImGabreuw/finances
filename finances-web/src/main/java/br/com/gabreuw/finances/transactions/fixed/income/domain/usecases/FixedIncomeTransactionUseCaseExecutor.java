package br.com.gabreuw.finances.transactions.fixed.income.domain.usecases;

import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.shared.usecase.UseCaseExecutor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FixedIncomeTransactionUseCaseExecutor implements UseCaseExecutor {

    @Override
    public <R, I extends UseCase.InputValues, O extends UseCase.OutputValues> R execute(UseCase<I, O> useCase, I inputValues, Function<O, R> mapper) {
        var output = useCase.execute(inputValues);
        return mapper.apply(output);
    }

}
