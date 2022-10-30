package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AverageCeilingPriceUseCaseTest {

    private AverageCeilingPriceUseCase underTest;

    @BeforeEach
    void setUp() {
        underTest = new AverageCeilingPriceUseCase();
    }

    @DisplayName("Should calculate average ceiling price")
    @Test
    void shouldCalculateAverageCeilingPrice() {
        List<Double> profitLast5Years = List.of(2_213_710_000.0, 2_262_930_000.0, 1_002_320_000.0, 1_071_310_000.0, 648_290_000.0);
        var numberOfShares = 1_033_496_721;
        var payoutLast5Years = 67.98;

        var input = new AverageCeilingPriceUseCase.InputValues(profitLast5Years, numberOfShares, payoutLast5Years);

        var output = underTest.execute(input);

        assertThat(output.averageCeilingPrice()).isEqualTo(15.78);
    }

}