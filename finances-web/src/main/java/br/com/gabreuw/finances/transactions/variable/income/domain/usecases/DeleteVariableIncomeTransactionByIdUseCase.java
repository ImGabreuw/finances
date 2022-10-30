package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteVariableIncomeTransactionByIdUseCase implements UseCase<DeleteVariableIncomeTransactionByIdUseCase.InputValues, DeleteVariableIncomeTransactionByIdUseCase.OutputValues> {

    private final VariableIncomeTransactionRepository variableIncomeTransactionRepository;

    @Override
    public OutputValues execute(InputValues input) {
        var id = input.id();

        variableIncomeTransactionRepository.deleteById(id);

        return new OutputValues();
    }

    public record InputValues(Long id) implements UseCase.InputValues {}

    public record OutputValues() implements UseCase.OutputValues {}

}
