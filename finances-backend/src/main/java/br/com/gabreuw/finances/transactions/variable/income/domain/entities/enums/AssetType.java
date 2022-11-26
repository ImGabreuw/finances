package br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum AssetType {

    STOCK("ação"),
    ETF("ETF"),
    REAL_STATE_FUND("FII");

    private final String description;

    public static List<String> getNameOfAssetTypesExcluding(AssetType... filter) {
        return Arrays.stream(AssetType.values())
                .filter(assetType -> !Arrays.asList(filter).contains(assetType))
                .map(AssetType::name)
                .collect(Collectors.toList());
    }

    public static AssetType random() {
        var index = NumberHelper.randomIntegerBetween(0, AssetType.values().length - 1);
        return AssetType.values()[index];
    }

}
