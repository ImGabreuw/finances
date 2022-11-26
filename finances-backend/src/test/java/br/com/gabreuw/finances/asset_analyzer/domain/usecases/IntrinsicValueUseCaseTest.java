package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntrinsicValueUseCaseTest {

    private IntrinsicValueUseCase underTest;

    @BeforeEach
    void setUp() {
        underTest = new IntrinsicValueUseCase();
    }

    @DisplayName("Should calculate the intrinsic value")
    @Test
    void shouldCalculateTheIntrinsicValue() {
        var LPA = 6.05;
        var VPA = 20.77;

        var input = new IntrinsicValueUseCase.InputValues(LPA, VPA);

        var output = underTest.execute(input);

        assertThat(output.intrinsicValue()).isEqualTo(53.17);
    }

}