package br.com.gabreuw.finances.investment_portfolio.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {

    private String name;

    private String email;

    private String password;

    private List<FixedIncomeAsset> fixedIncomeAssets = new ArrayList<>();

    private List<VariableIncomeAsset> variableIncomeAssets = new ArrayList<>();

    public List<VariableIncomeAsset> getWorthVariableIncomeAssetToBuy() {
        return variableIncomeAssets.stream()
                .filter(VariableIncomeAsset::isWorthToBuy)
                .collect(Collectors.toList());
    }

}
