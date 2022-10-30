package br.com.gabreuw.finances.interest.calculator.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import static lombok.AccessLevel.PRIVATE;

@AllArgsConstructor(access = PRIVATE)
@Data
public class AnnualInterestRate {

    private final Double value;

    public static AnnualInterestRate create(double annualInterestRate) {
        return new AnnualInterestRate(annualInterestRate);
    }

    public static AnnualInterestRate createFrom(double monthlyInterestRate) {
        return new AnnualInterestRate(monthlyInterestRate * 12);
    }

}
