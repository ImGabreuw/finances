package br.com.gabreuw.finances.asset_analyzer.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AnalysisResult {

    private Classification classification;

    private String description;

    private Object value;

    public AnalysisResult(String description, Object value) {
        this.description = description;
        this.value = value;
    }

    enum Classification {
        POSITIVE,
        NEUTRAL,
        NEGATIVE
    }

}
