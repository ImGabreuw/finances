package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectiveCeilingPriceUseCaseTest {

    private ProjectiveCeilingPriceUseCase underTest;

    @BeforeEach
    void setUp() {
        underTest = new ProjectiveCeilingPriceUseCase();
    }

    @DisplayName("Should calculate projective ceiling price")
    @Test
    void shouldCalculateProjectiveCeilingPrice() {
        var firstQuarterProfit = 559_920_000.0;
        var numberOfShares = 1_033_496_721;
        var averagePayout = 67.98;

        var input = new ProjectiveCeilingPriceUseCase.InputValues(firstQuarterProfit, numberOfShares, averagePayout);

        var output = underTest.execute(input);

        assertThat(output.conservativeProjectiveCeilingPrice()).isEqualTo(18.41);
        assertThat(output.optimisticProjectiveCeilingPrice()).isEqualTo(24.55);
    }

}