package br.com.gabreuw.finances.interest.calculator.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@Data
public class PeriodInYears {

    private final Integer value;

    public static PeriodInYears create(int periodInYears) {
        return new PeriodInYears(periodInYears);
    }

    public static PeriodInYears createFrom(int periodInMonths) {
        return new PeriodInYears(periodInMonths / 12);
    }

}
