package br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums;

import br.com.gabreuw.finances.shared.helper.NumberHelper;

public enum Indexer {

    IPCA,
    CDI;

    public static Indexer random() {
        return Indexer.values()[NumberHelper.randomIntegerBetween(0, 1)];
    }

}
