package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllVariableIncomeTransactionByAssetTypeUseCase implements UseCase<FindAllVariableIncomeTransactionByAssetTypeUseCase.InputValues, FindAllVariableIncomeTransactionByAssetTypeUseCase.OutputValues> {

    private final VariableIncomeTransactionRepository variableIncomeTransactionRepository;

    @Override
    public OutputValues execute(InputValues input) {
        var assetType = AssetType.valueOf(input.assetType());
        var pageInfo = input.pageInfo();

        var transactions = variableIncomeTransactionRepository.findAllByAssetType(assetType, pageInfo);

        return new OutputValues(transactions);
    }

    public record InputValues(String assetType, PageInfo pageInfo) implements UseCase.InputValues {
    }

    public record OutputValues(List<VariableIncomeTransaction> variableIncomeTransactions) implements UseCase.OutputValues {
    }

}
