package br.com.gabreuw.finances.interest.calculator.helper;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class CompoundInterestFormula {

    /**
     * @param initialValue Starting capital
     * @param interestRate  Annual interest tax in percentage
     * @param period       Period in years
     * @return Final amount
     */
    public static double calculate(double initialValue, double interestRate, int period) {
        return initialValue * Math.pow((1 + interestRate / 100), period);
    }

}
