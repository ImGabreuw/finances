package br.com.gabreuw.finances.bank_account.domain.usecases;

import br.com.gabreuw.finances.bank_account.domain.adapters.CsvStatementReader;
import br.com.gabreuw.finances.bank_account.domain.entities.BankAccountTransaction;
import br.com.gabreuw.finances.shared.logging.ApplicationLogger;
import br.com.gabreuw.finances.shared.logging.LogContext;
import br.com.gabreuw.finances.shared.logging.LoggerFactory;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReadStatementFromCsvFileUseCase implements UseCase<ReadStatementFromCsvFileUseCase.InputValues, ReadStatementFromCsvFileUseCase.OutputValues> {

    private static final ApplicationLogger LOGGER = LoggerFactory.getLogger(ReadStatementFromCsvFileUseCase.class);

    private final CsvStatementReader csvStatementReader;

    @Override
    public OutputValues execute(InputValues input) {
        try {
            LogContext.putCorrelationId();
            LogContext.putComponent("ReadStatementFromCsvFileUseCase");
            LogContext.put("file.path", input.csvFilePath());

            LOGGER.info("Iniciando processamento do extrato CSV");

            String csvFilePath = input.csvFilePath();

            if (csvFilePath == null || csvFilePath.isBlank()) {
                LOGGER.error("Caminho do arquivo CSV inválido: nulo ou vazio");
                throw new IllegalArgumentException("CSV file path cannot be null or empty");
            }

            Path csvPath = Path.of(csvFilePath);
            LogContext.put("file.name", csvPath.getFileName().toString());

            if (!csvPath.toFile().exists()) {
                LOGGER.error("Arquivo CSV não encontrado no caminho especificado");
                throw new IllegalArgumentException("CSV file does not exist at the specified path: " + csvFilePath);
            }

            try (var inputStream = csvPath.toUri().toURL().openStream()) {
                LOGGER.debug("Lendo conteúdo do arquivo CSV");

                List<BankAccountTransaction> transactions = csvStatementReader.read(inputStream);

                LOGGER.info("Processamento do extrato CSV concluído com sucesso, registros={}", transactions.size());
                return new OutputValues(transactions);
            } catch (Exception e) {
                LOGGER.error("Falha ao processar arquivo CSV: " + e.getMessage(), e);
                throw new RuntimeException("Error reading CSV file: " + e.getMessage(), e);
            }
        } finally {
            LogContext.clear();
        }
    }

    public record InputValues(String csvFilePath) implements UseCase.InputValues {
    }

    public record OutputValues(List<BankAccountTransaction> transactions) implements UseCase.OutputValues {
    }

}
