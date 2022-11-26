package br.com.gabreuw.finances.transactions.fixed.income.domain.usecases;

import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.FixedIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public record SaveFixedIncomeTransactionUseCase(
        FixedIncomeTransactionRepository fixedIncomeTransactionRepository
) implements UseCase<SaveFixedIncomeTransactionUseCase.InputValues, SaveFixedIncomeTransactionUseCase.OutputValues> {

    @Override
    public SaveFixedIncomeTransactionUseCase.OutputValues execute(SaveFixedIncomeTransactionUseCase.InputValues input) {
        var assetType = input.assetType();
        var issuingAgency = input.issuingAgency();
        var description = input.description();
        var profitabilityInPercent = input.profitabilityInPercent();
        var indexer = input.indexer();
        var liquidity = input.liquidity();
        var expiresIn = input.expiresIn();
        var totalInvested = input.totalInvested();
        var units = input.units();
        var operationDate = input.operationDate();

        var transaction = FixedIncomeTransaction.builder()
                .assetType(assetType)
                .issuingAgency(issuingAgency)
                .description(description)
                .profitabilityInPercent(profitabilityInPercent)
                .indexer(indexer)
                .liquidity(liquidity)
                .expiresIn(expiresIn)
                .totalInvested(totalInvested)
                .units(units)
                .operationDate(operationDate)
                .build();

        var savedTransaction = fixedIncomeTransactionRepository.save(transaction);

        return new OutputValues(savedTransaction);
    }

    public record InputValues(
            AssetType assetType,
            String issuingAgency,
            String description,
            Double profitabilityInPercent,
            Indexer indexer,
            String liquidity,
            LocalDate expiresIn,
            Double totalInvested,
            Double units,
            LocalDate operationDate
    ) implements UseCase.InputValues {
    }

    public record OutputValues(FixedIncomeTransaction fixedIncomeTransaction) implements UseCase.OutputValues {
    }

}
