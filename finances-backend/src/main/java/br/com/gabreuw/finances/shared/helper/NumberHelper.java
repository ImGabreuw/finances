package br.com.gabreuw.finances.shared.helper;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import java.util.Locale;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class NumberHelper {

    static {
        Locale.setDefault(Locale.US);
    }

    private static final Faker FAKER = Faker.instance();

    public static Double round(Number value) {
        return NumberHelper.round(value, 2);
    }

    public static Double round(Number value, int digits) {
        var template = "%." + digits + "f";

        return Double.parseDouble(template.formatted(value));
    }

    public static Double randomOperationAverageCost() {
        return FAKER.number().randomDouble(2, 3, 100);
    }

    public static Integer randomNumberOfShares() {
        return FAKER.number().numberBetween(1, 20);
    }

    public static Integer randomIntegerBetween(int start, int end) {
        return FAKER.number().numberBetween(start, end);
    }

    public static Double randomDoubleBetween(long start, long end) {
        return FAKER.number().randomDouble(2, start, end);
    }

    public static boolean isDouble(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Double parseDouble(String text) {
        if (!isDouble(text)) {
            return null;
        }

        return Double.parseDouble(text);
    }

    public static boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Integer parseInteger(String text) {
        if (!isInteger(text)) {
            return null;
        }

        return Integer.parseInt(text);
    }

    public static int getIntValue(double value) {
        return Double.valueOf(value).intValue();
    }

    public static String removePercentageSymbol(String text) {
        return text.trim().replace("%", "");
    }

    public static String removeNumberFormat(String text) {
        return text.trim().replace(".", "").replace(",", ".");
    }

}
