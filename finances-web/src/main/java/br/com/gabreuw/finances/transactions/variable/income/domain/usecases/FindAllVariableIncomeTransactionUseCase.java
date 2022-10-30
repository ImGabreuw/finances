package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllVariableIncomeTransactionUseCase implements UseCase<FindAllVariableIncomeTransactionUseCase.InputValues, FindAllVariableIncomeTransactionUseCase.OutputValues> {

    private final VariableIncomeTransactionRepository variableIncomeTransactionRepository;

    @Override
    public OutputValues execute(InputValues input) {
        var pageInfo = input.pageInfo();

        var transactions = variableIncomeTransactionRepository.findAll(pageInfo);

        return new OutputValues(transactions);
    }

    public record InputValues(PageInfo pageInfo) implements UseCase.InputValues {
    }

    public record OutputValues(List<VariableIncomeTransaction> variableIncomeTransactions) implements UseCase.OutputValues {
    }

}
