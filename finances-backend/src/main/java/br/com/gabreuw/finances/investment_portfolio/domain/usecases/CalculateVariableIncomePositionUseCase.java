package br.com.gabreuw.finances.investment_portfolio.domain.usecases;

import br.com.gabreuw.finances.investment_portfolio.domain.entities.VariableIncomeAsset;
import br.com.gabreuw.finances.investment_portfolio.domain.usecases.errors.DataInconsistencyException;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType.SALE;

@Component
public record CalculateVariableIncomePositionUseCase(
        VariableIncomeTransactionRepository transactionRepository
) implements UseCase<CalculateVariableIncomePositionUseCase.InputValues, CalculateVariableIncomePositionUseCase.OutputValues> {

    @Override
    public CalculateVariableIncomePositionUseCase.OutputValues execute(CalculateVariableIncomePositionUseCase.InputValues input) {
        var assets = Arrays.stream(AssetType.values())
                .map(this::makePositionByAssetType)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return new OutputValues(assets);
    }

    private List<VariableIncomeAsset> makePositionByAssetType(AssetType assetType) {
        var portfolioAssets = transactionRepository.findAllByAssetType(assetType)
                .stream()
                .map(VariableIncomeTransaction::getAssetCode)
                .distinct()
                .toList();

        List<VariableIncomeAsset> assets = new ArrayList<>();

        for (String assetCode : portfolioAssets) {
            var assetTransactions = transactionRepository.findAllByAssetCode(assetCode);

            int numberOfShares = 0;
            double totalInvested = 0.0;

            for (VariableIncomeTransaction transaction : assetTransactions) {
                if (transaction.getOperationType() == SALE) {
                    numberOfShares -= transaction.getNumberOfShares();
                    totalInvested -= transaction.getTotalInvested();
                    continue;
                }

                numberOfShares += transaction.getNumberOfShares();
                totalInvested += transaction.getTotalInvested();
            }

            if (numberOfShares < 0 || totalInvested < 0) {
                throw new DataInconsistencyException("O 'número de cotas' ou 'total investido' não podem ter valores negativos. Por favor entrar em contato com o suporte.");
            }

            var transactionAssetType = assetTransactions.get(0).getAssetType();

            var asset = VariableIncomeAsset
                    .builder()
                    .code(assetCode)
                    .type(transactionAssetType)
                    .numberOfShares(numberOfShares)
                    .averagePrice(totalInvested / numberOfShares)
                    .build();

            assets.add(asset);
        }

        return assets;
    }

    public record InputValues() implements UseCase.InputValues {
    }

    public record OutputValues(List<VariableIncomeAsset> assets) implements UseCase.OutputValues {
    }

}
