package br.com.gabreuw.finances.interest.calculator.domain.entities;

import br.com.gabreuw.finances.interest.calculator.helper.CompoundInterestFormula;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompoundInterestCalculator {

    private Double initialValue;

    private AnnualInterestRate interestRate;

    private PeriodInYears period;

    public Double calculateFinalValue() {
        return CompoundInterestFormula.calculate(
                this.initialValue,
                this.interestRate.getValue(),
                this.period.getValue()
        );
    }

}
