package br.com.gabreuw.finances.transactions.variable.income.application.config.database;

import br.com.gabreuw.finances.shared.helper.VariableIncomeTransactionHelper;
import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@RequiredArgsConstructor
public class VariableIncomeDataBaseSeeder implements CommandLineRunner {

    private final VariableIncomeTransactionRepository variableIncomeTransactionRepository;

    @Override
    public void run(String... args) {
        var transactionHelper = VariableIncomeTransactionHelper.getINSTANCE();

        transactionHelper
                .generateTransactions(PageInfo.createDefault().getPageSize())
                .forEach(variableIncomeTransactionRepository::save);
    }

}
