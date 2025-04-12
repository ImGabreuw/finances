package br.com.gabreuw.finances.bank_account.domain.usecases;

import br.com.gabreuw.finances.utils.TestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReadStatementFromCsvFileUseCaseTest {

    @Autowired
    private ReadStatementFromCsvFileUseCase underTest;

    @Test
    void shouldReadNuBankStatementFromCsvFile() throws URISyntaxException {
        String testResource = "NU_998554159_01MAR2025_31MAR2025.csv";

        String testResourcePath = TestHelper.getTestResourceAbsolutePath(
                ReadStatementFromCsvFileUseCaseTest.class,
                testResource
        );

        var input = new ReadStatementFromCsvFileUseCase.InputValues(testResourcePath);
        var output = underTest.execute(input);

        assertThat(output).isNotNull();
        assertThat(output.transactions()).isNotEmpty();
        assertThat(output.transactions()).hasSize(56);
    }
}