package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SafetyMarginUseCaseTest {

    private SafetyMarginUseCase underTest;

    @BeforeEach
    void setUp() {
        underTest = new SafetyMarginUseCase();
    }

    @DisplayName("Should calculate safety margin")
    @Test
    void shouldCalculateSafetyMargin() {
        var intrinsicValue = 53.17;
        var stockPrice = 42.26;

        var input = new SafetyMarginUseCase.InputValues(intrinsicValue, stockPrice);

        var output = underTest.execute(input);

        assertThat(output.safetyMarginInPercentage()).isEqualTo(25.82);
    }

}