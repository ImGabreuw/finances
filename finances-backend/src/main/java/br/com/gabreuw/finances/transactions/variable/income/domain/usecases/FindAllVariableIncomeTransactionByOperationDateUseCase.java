package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllVariableIncomeTransactionByOperationDateUseCase implements UseCase<FindAllVariableIncomeTransactionByOperationDateUseCase.InputValues, FindAllVariableIncomeTransactionByOperationDateUseCase.OutputValues> {

    private final VariableIncomeTransactionRepository variableIncomeTransactionRepository;

    @Override
    public OutputValues execute(InputValues input) {
        var operationDate = input.operationDate();
        var pageInfo = input.pageInfo();

        var transactions = variableIncomeTransactionRepository.findAllByOperationDate(operationDate, pageInfo);

        return new OutputValues(transactions);
    }

    public record InputValues(LocalDate operationDate, PageInfo pageInfo) implements UseCase.InputValues {}

    public record OutputValues(List<VariableIncomeTransaction> variableIncomeTransactions) implements UseCase.OutputValues {}

}
