package br.com.gabreuw.finances.interest.calculator.domain.entities;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CompoundInterestCalculatorTest {

    @DisplayName("Should calculate compound interest")
    @Test
    void shouldCalculateCompoundInterest() {
        double initialAmount = 1_000.00;
        AnnualInterestRate interestRate = AnnualInterestRate.create(12);
        PeriodInYears period = PeriodInYears.create(1);

        CompoundInterestCalculator calculator = new CompoundInterestCalculator(initialAmount, interestRate, period);

        Double finalValue = calculator.calculateFinalValue();

        assertThat(finalValue).isEqualTo(1120.0);
    }

    @DisplayName("Should calculate compound interest with monthly inputs")
    @Test
    void shouldCalculateCompoundInterestWithMonthlyInputs() {
        double initialAmount = 1_000.00;
        AnnualInterestRate interestRate = AnnualInterestRate.createFrom(1);
        PeriodInYears period = PeriodInYears.createFrom(12);

        CompoundInterestCalculator calculator = new CompoundInterestCalculator(initialAmount, interestRate, period);

        Double finalValue = calculator.calculateFinalValue();

        assertThat(finalValue).isEqualTo(1120.0);
    }

}