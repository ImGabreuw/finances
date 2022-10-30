package br.com.gabreuw.finances.transactions.variable.income.domain.usecases;

import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SaveVariableIncomeTransactionUseCase implements UseCase<SaveVariableIncomeTransactionUseCase.InputValues, SaveVariableIncomeTransactionUseCase.OutputValues> {

    private final VariableIncomeTransactionRepository variableIncomeTransactionRepository;

    @Override
    public OutputValues execute(InputValues input) {
        var assetCode = input.assetCode();
        var assetType = input.assetType();
        var operationDate = input.operationDate();
        var operationType = input.operationType();
        var operationAverageCost = input.operationAverageCost();
        var numberOfShares = input.numberOfShares();

        var transaction = VariableIncomeTransaction.create(
                assetCode,
                assetType,
                operationDate,
                operationType,
                operationAverageCost,
                numberOfShares
        );

        var savedTransaction = variableIncomeTransactionRepository.save(transaction);

        return new OutputValues(savedTransaction);
    }

    public record InputValues(
            String assetCode,
            AssetType assetType,
            LocalDate operationDate,
            OperationType operationType,
            Double operationAverageCost,
            Integer numberOfShares
    ) implements UseCase.InputValues {
    }

    public record OutputValues(VariableIncomeTransaction variableIncomeTransaction) implements UseCase.OutputValues {
    }

}
