package br.com.gabreuw.finances.asset_analyzer.domain.usecases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CurrentCeilingPriceUseCaseTest {
    
    private CurrentCeilingPriceUseCase underTest;

    @BeforeEach
    void setUp() {
        underTest = new CurrentCeilingPriceUseCase();
    }

    @DisplayName("Should calculate current ceiling price")
    @Test
    void shouldCalculateCurrentCeilingPrice() {
        var DPALast12Months = 5.3121;

        var input = new CurrentCeilingPriceUseCase.InputValues(DPALast12Months);

        var output = underTest.execute(input);

        assertThat(output.currentCeilingPrice()).isEqualTo(88.54);
    }
    
}