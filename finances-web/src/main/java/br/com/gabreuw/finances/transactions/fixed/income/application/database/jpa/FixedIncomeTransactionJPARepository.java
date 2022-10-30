package br.com.gabreuw.finances.transactions.fixed.income.application.database.jpa;

import br.com.gabreuw.finances.transactions.fixed.income.application.database.entities.FixedIncomeTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedIncomeTransactionJPARepository extends JpaRepository<FixedIncomeTransactionEntity, Long> {
}
