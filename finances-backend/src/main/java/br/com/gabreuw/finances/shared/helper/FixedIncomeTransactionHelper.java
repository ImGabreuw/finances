package br.com.gabreuw.finances.shared.helper;

import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer;
import com.github.javafaker.Faker;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FixedIncomeTransactionHelper {

    @Getter
    private static final FixedIncomeTransactionHelper INSTANCE = new FixedIncomeTransactionHelper();

    private final Faker faker;

    private FixedIncomeTransactionHelper() {
        this.faker = Faker.instance();
    }

    public Double randomProfitabilityInPercent() {
        return NumberHelper.randomDoubleBetween(4, 17);
    }

    public String randomLiquidity() {
        return faker.random().nextBoolean() ?
                "diária" :
                "no vencimento";
    }

    public Double randomTotalInvested(Double units) {
        return units * 1_000.0;
    }

    public Double randomUnits() {
        return NumberHelper.randomDoubleBetween(1, 10);
    }

    public List<FixedIncomeTransaction> generateTransactions(int maxElements) {
        return Stream.generate(this::createFakeTransactions)
                .flatMap(List::stream)
                .limit(maxElements)
                .collect(Collectors.toList());
    }

    private List<FixedIncomeTransaction> createFakeTransactions() {
        return Arrays.stream(AssetType.values())
                .map(assetType -> {
                    var transaction = createFakeTransaction();
                    transaction.setAssetType(assetType);
                    return transaction;
                })
                .toList();
    }

    public FixedIncomeTransaction createFakeTransaction() {
        var units = randomUnits();
        var totalInvested = randomTotalInvested(units);

        var expiresIn = LocalDateHelper.randomFutureDate();
        var operationDate = LocalDateHelper.randomDateFromNowTo(expiresIn);

        return FixedIncomeTransaction.builder()
                .assetType(AssetType.random())
                .issuingAgency(faker.company().name())
                .profitabilityInPercent(randomProfitabilityInPercent())
                .indexer(Indexer.random())
                .liquidity(randomLiquidity())
                .expiresIn(expiresIn)
                .totalInvested(totalInvested)
                .units(units)
                .operationDate(operationDate)
                .build();
    }

}
