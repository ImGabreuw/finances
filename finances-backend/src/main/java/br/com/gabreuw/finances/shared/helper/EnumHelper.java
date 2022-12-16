package br.com.gabreuw.finances.shared.helper;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class EnumHelper {

    public static <T extends Enum<T>> T randomValue(Class<T> enumClass) {
        var values = enumClass.getEnumConstants();
        var index = NumberHelper.randomIntegerBetween(0, values.length);

        return values[index];
    }

}
