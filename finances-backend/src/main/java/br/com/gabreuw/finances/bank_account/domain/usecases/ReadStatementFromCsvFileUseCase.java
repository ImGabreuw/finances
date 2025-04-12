package br.com.gabreuw.finances.bank_account.domain.usecases;

import br.com.gabreuw.finances.bank_account.domain.adapters.CsvStatementReader;
import br.com.gabreuw.finances.bank_account.domain.entities.BankAccountTransaction;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReadStatementFromCsvFileUseCase implements UseCase<ReadStatementFromCsvFileUseCase.InputValues, ReadStatementFromCsvFileUseCase.OutputValues> {

    private final CsvStatementReader csvStatementReader;

    @Override
    public OutputValues execute(InputValues input) {
        String csvFilePath = input.csvFilePath();

        if (csvFilePath == null || csvFilePath.isBlank()) {
            throw new IllegalArgumentException("CSV file path cannot be null or empty");
        }

        Path csvPath = Path.of(csvFilePath);

        if (!csvPath.toFile().exists()) {
            throw new IllegalArgumentException("CSV file does not exist at the specified path: " + csvFilePath);
        }

        try (var inputStream = csvPath.toUri().toURL().openStream()) {
            List<BankAccountTransaction> transactions = csvStatementReader.read(inputStream);
            return new OutputValues(transactions);
        } catch (Exception e) {
            throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
        }
    }

    public record InputValues(String csvFilePath) implements UseCase.InputValues {
    }

    public record OutputValues(List<BankAccountTransaction> transactions) implements UseCase.OutputValues {
    }

}
