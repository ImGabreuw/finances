package br.com.gabreuw.finances.shared.usecase;

import java.util.function.Function;

public interface UseCaseExecutor {

    <R, I extends UseCase.InputValues, O extends UseCase.OutputValues> R execute(
            UseCase<I, O> useCase,
            I inputValues,
            Function<O, R> mapper
    );

}
