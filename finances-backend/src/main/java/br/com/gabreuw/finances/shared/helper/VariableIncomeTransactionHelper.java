package br.com.gabreuw.finances.shared.helper;

import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType.BUY;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class VariableIncomeTransactionHelper {

    @Getter
    private static final VariableIncomeTransactionHelper INSTANCE = new VariableIncomeTransactionHelper();

    public List<VariableIncomeTransaction> generateTransactions(int maxElements) {
        return Stream.generate(this::createFakeTransactions)
                .flatMap(List::stream)
                .limit(maxElements)
                .collect(Collectors.toList());
    }

    private List<VariableIncomeTransaction> createFakeTransactions(AssetType... filter) {
        var fakeAssets = FakeAsset.getFakeAssetExcluding(filter);

        List<VariableIncomeTransaction> fakeVariableIncomeTransactions = new ArrayList<>();

        for (FakeAsset fakeAsset : fakeAssets) {
            var assetType = AssetType.valueOf(fakeAsset.name());

            for (String code : fakeAsset.getCodes()) {
                fakeVariableIncomeTransactions.add(VariableIncomeTransaction.create(
                        code,
                        assetType,
                        LocalDateHelper.randomPastDate(),
                        BUY,
                        NumberHelper.randomOperationAverageCost(),
                        NumberHelper.randomNumberOfShares()
                ));
            }
        }

        return fakeVariableIncomeTransactions;
    }

    private enum FakeAsset {
        STOCK("BBAS3", "TAEE11", "VALE3", "PETR3", "BBSE3"),
        ETF("IVVB11", "BOVA11"),
        REAL_STATE_FUND("TRXF11", "BTLG11", "VISC11", "HGRE11", "MXRF11");

        @Getter
        private final List<String> codes;

        FakeAsset(String... codes) {
            this.codes = Arrays.asList(codes);
        }

        public static List<FakeAsset> getFakeAssetExcluding(AssetType... filter) {
            var filtersName = AssetType.getNameOfAssetTypesExcluding(filter);

            return Arrays.stream(FakeAsset.values())
                    .filter(fakeAsset -> filtersName.contains(fakeAsset.name()))
                    .toList();
        }
    }

}
