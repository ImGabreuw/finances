package br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums;

import br.com.gabreuw.finances.shared.helper.NumberHelper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AssetType {

    CDB("Certificado de Depósito Interbancário"),
    DIRECT_TREASURE("Tesouro Direto"),
    LCA("Letra de Crédito do Agronegócio"),
    LCI("Letra de Crédito Imobiliário");

    private final String description;

    public static AssetType random() {
        var index = NumberHelper.randomIntegerBetween(0, AssetType.values().length - 1);
        return AssetType.values()[index];
    }

}
