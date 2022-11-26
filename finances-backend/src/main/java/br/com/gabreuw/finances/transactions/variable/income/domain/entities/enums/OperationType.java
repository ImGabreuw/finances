package br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OperationType {

    BUY("compra"),
    SALE("venda");

    private final String description;

    public static OperationType random() {
        var faker = Faker.instance();

        var index = faker
                .random()
                .nextInt(0, OperationType.values().length - 1);

        return OperationType.values()[index];
    }

}
