package br.com.gabreuw.finances.asset_analyzer.domain.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import static br.com.gabreuw.finances.asset_analyzer.domain.entities.AnalysisResult.Classification.*;

@RequiredArgsConstructor
@Data
public abstract class Criteria {

    private final String description;

    private final Object value;

    public abstract boolean isPositive();

    public abstract boolean isNegative();

    public AnalysisResult analyse() {
        var result = new AnalysisResult(description, value);

        if (isPositive()) {
            result.setClassification(POSITIVE);
            return result;
        }

        if (isNegative()) {
            result.setClassification(NEGATIVE);
            return result;
        }

        result.setClassification(NEUTRAL);
        return result;
    }

}
