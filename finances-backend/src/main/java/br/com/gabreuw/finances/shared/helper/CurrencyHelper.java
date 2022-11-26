package br.com.gabreuw.finances.shared.helper;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CurrencyHelper {

    public static String removeBrazilianCurrencySymbol(String text) {
        return text.trim().replace("R$", "");
    }

}
