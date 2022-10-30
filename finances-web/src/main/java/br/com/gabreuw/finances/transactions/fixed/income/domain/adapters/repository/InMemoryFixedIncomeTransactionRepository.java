package br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository;

import br.com.gabreuw.finances.shared.helper.FixedIncomeTransactionHelper;
import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryFixedIncomeTransactionRepository implements FixedIncomeTransactionRepository {

    private final Map<Long, FixedIncomeTransaction> database = new ConcurrentHashMap<>();

    public InMemoryFixedIncomeTransactionRepository() {
        var pageInfo = PageInfo.createDefault();

        var transactionHelper = FixedIncomeTransactionHelper.getINSTANCE();

        transactionHelper
                .generateTransactions(pageInfo.getPageSize())
                .forEach(this::save);
    }

    private Long getNextId() {
        if (database.isEmpty()) {
            return 1L;
        }

        return database.size() + 1L;
    }

    @Override
    public FixedIncomeTransaction save(FixedIncomeTransaction fixedIncomeTransaction) {
        var id = getNextId();

        if (fixedIncomeTransaction.getId() == null) {
            fixedIncomeTransaction.setId(id);
        }

        database.put(id, fixedIncomeTransaction);

        return fixedIncomeTransaction;
    }

    @Override
    public void deleteById(Long id) {
        database.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return database.containsKey(id);
    }

    @Override
    public Optional<FixedIncomeTransaction> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<FixedIncomeTransaction> findAll(PageInfo pageInfo) {
        return new ArrayList<>(database.values());
    }

}
