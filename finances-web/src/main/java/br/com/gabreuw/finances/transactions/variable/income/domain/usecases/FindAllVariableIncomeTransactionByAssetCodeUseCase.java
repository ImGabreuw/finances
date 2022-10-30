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
public class FindAllVariableIncomeTransactionByAssetCodeUseCase implements UseCase<FindAllVariableIncomeTransactionByAssetCodeUseCase.InputValues, FindAllVariableIncomeTransactionByAssetCodeUseCase.OutputValues> {

    private final VariableIncomeTransactionRepository variableIncomeTransactionRepository;

    @Override
    public OutputValues execute(InputValues input) {
        var assetCode = input.assetCode();
        var pageInfo = input.pageInfo();

        var transactions = variableIncomeTransactionRepository.findAllByAssetCode(assetCode, pageInfo);

        return new OutputValues(transactions);
    }

    public record InputValues(String assetCode, PageInfo pageInfo) implements UseCase.InputValues {}

    public record OutputValues(List<VariableIncomeTransaction> variableIncomeTransactions) implements UseCase.OutputValues {}

}
