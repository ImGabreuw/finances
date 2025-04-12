package br.com.gabreuw.finances.bank_account.domain.adapters;

import br.com.gabreuw.finances.bank_account.domain.entities.BankAccountTransaction;

import java.io.InputStream;
import java.util.List;

public interface CsvStatementReader {

    List<BankAccountTransaction> read(InputStream is);

}
