package br.com.gabreuw.finances.shared.helper;

import com.github.javafaker.Faker;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class InternetHelper {

    private static final Faker FAKER = Faker.instance();

    public static String randomUrl() {
        var domainName = FAKER.internet().domainName();

        return "https://www." + domainName;
    }

}
