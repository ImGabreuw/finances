package br.com.gabreuw.finances.bank_account.application.readers.nubank;

import br.com.gabreuw.finances.bank_account.domain.adapters.CsvStatementReader;
import br.com.gabreuw.finances.bank_account.domain.entities.BankAccountTransaction;
import br.com.gabreuw.finances.bank_account.domain.entities.enums.BankAccountTransactionNature;
import br.com.gabreuw.finances.shared.helper.LocalDateHelper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@Component
public class NuBankCsvStatementReader implements CsvStatementReader {

    @Override
    public List<BankAccountTransaction> read(InputStream is) {
        try (InputStreamReader reader = new InputStreamReader(is)) {
            CsvToBean<NuBankCsvStatementEntry> csvToBean = new CsvToBeanBuilder<NuBankCsvStatementEntry>(reader)
                    .withType(NuBankCsvStatementEntry.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<NuBankCsvStatementEntry> entries = csvToBean.parse();

            return entries.stream()
                    .map(this::convertToBankAccountTransaction)
                    .toList();
        } catch (Exception e) {
            log.warn("Error reading CSV file: {}", e.getMessage());
            return List.of();
        }
    }

    private BankAccountTransaction convertToBankAccountTransaction(NuBankCsvStatementEntry entry) {
        LocalDate transactionDate = LocalDateHelper.parse(entry.getDate(), "dd/MM/yyyy");

        BankAccountTransaction.BankAccountTransactionBuilder builder = BankAccountTransaction
                .builder()
                .bankAccountId(1L)
                .transactionDate(transactionDate)
                .description(entry.getDescription())
                .bankIdentifier(entry.getIdentifier());

        double amount = Double.parseDouble(entry.getValue());

        if (amount < 0) {
            builder
                    .amount(Math.abs(amount))
                    .nature(BankAccountTransactionNature.EXPENSE);
        } else {
            builder
                    .amount(amount)
                    .nature(BankAccountTransactionNature.INCOME);
        }

        return builder.build();
    }

}
