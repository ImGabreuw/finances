package br.com.gabreuw.finances.transactions.fixed.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.FixedIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record FindAllFixedIncomeTransactionUseCase(
        FixedIncomeTransactionRepository fixedIncomeTransactionRepository
) implements UseCase<FindAllFixedIncomeTransactionUseCase.InputValues, FindAllFixedIncomeTransactionUseCase.OutputValues> {

    @Override
    public OutputValues execute(InputValues input) {
        var pageInfo = input.pageInfo();

        var transactions = fixedIncomeTransactionRepository.findAll(pageInfo);

        return new OutputValues(transactions);
    }

    public record InputValues(PageInfo pageInfo) implements UseCase.InputValues {
    }

    public record OutputValues(List<FixedIncomeTransaction> fixedIncomeTransactions) implements UseCase.OutputValues {
    }

}
