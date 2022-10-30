package br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;

import java.util.List;
import java.util.Optional;

public interface FixedIncomeTransactionRepository {

    FixedIncomeTransaction save(FixedIncomeTransaction fixedIncomeTransaction);

    void deleteById(Long id);

    boolean existsById(Long id);

    Optional<FixedIncomeTransaction> findById(Long id);

    List<FixedIncomeTransaction> findAll(PageInfo pageInfo);

}
