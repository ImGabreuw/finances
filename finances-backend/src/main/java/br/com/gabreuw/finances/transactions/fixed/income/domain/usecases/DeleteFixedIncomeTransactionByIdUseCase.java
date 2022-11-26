package br.com.gabreuw.finances.transactions.fixed.income.domain.usecases;

import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.FixedIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.errors.TransactionNotFoundException;
import org.springframework.stereotype.Component;

@Component
public record DeleteFixedIncomeTransactionByIdUseCase(
        FixedIncomeTransactionRepository fixedIncomeTransactionRepository
) implements UseCase<DeleteFixedIncomeTransactionByIdUseCase.InputValues, DeleteFixedIncomeTransactionByIdUseCase.OutputValues> {

    @Override
    public DeleteFixedIncomeTransactionByIdUseCase.OutputValues execute(DeleteFixedIncomeTransactionByIdUseCase.InputValues input) {
        var id = input.id();

        if (!fixedIncomeTransactionRepository.existsById(id)) {
            throw new TransactionNotFoundException(id);
        }

        fixedIncomeTransactionRepository.deleteById(id);

        return new OutputValues();
    }

    public record InputValues(Long id) implements UseCase.InputValues {
    }

    public record OutputValues() implements UseCase.OutputValues {
    }

}
