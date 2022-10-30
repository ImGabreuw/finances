package br.com.gabreuw.finances.shared.helper;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static java.util.concurrent.TimeUnit.DAYS;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class LocalDateHelper {

    private final static Faker FAKER = Faker.instance();

    public static LocalDate randomPastDate() {
        var date = FAKER.date().past(360, DAYS).toInstant();

        return LocalDate.ofInstant(date, ZoneId.systemDefault());
    }

    public static LocalDate randomDateBetween(LocalDate from, LocalDate to) {
        var date = FAKER.date().between(
                new Date(from.toEpochDay()),
                new Date(to.toEpochDay())
        );

        return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDate randomDateFromNowTo(LocalDate to) {
        return randomDateBetween(LocalDate.now(), to);
    }

    public static LocalDate randomFutureDate() {
        var date = FAKER.date().future(10 * 360, DAYS).toInstant();

        return LocalDate.ofInstant(date, ZoneId.systemDefault());
    }

    public static boolean isDate(String text) {
        try {
            LocalDate.parse(text);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate parse(String text) {
        if (!isDate(text)) {
            return null;
        }

        return LocalDate.parse(
                text,
                DateTimeFormatter.ISO_LOCAL_DATE
        );
    }

    public static LocalDate parse(String text, String format) {
        if (!isDate(text)) {
            return null;
        }

        return LocalDate.parse(
                text,
                DateTimeFormatter.ofPattern(format)
        );
    }

}
