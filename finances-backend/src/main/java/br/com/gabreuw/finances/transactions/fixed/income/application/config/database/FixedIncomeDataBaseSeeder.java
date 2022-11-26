package br.com.gabreuw.finances.transactions.fixed.income.application.config.database;

import br.com.gabreuw.finances.shared.helper.FixedIncomeTransactionHelper;
import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.FixedIncomeTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@RequiredArgsConstructor
public class FixedIncomeDataBaseSeeder implements CommandLineRunner {

    private final FixedIncomeTransactionRepository fixedIncomeTransactionRepository;

    @Override
    public void run(String... args) {
        var transactionHelper = FixedIncomeTransactionHelper.getINSTANCE();

        transactionHelper
                .generateTransactions(PageInfo.createDefault().getPageSize())
                .forEach(fixedIncomeTransactionRepository::save);
    }

}
