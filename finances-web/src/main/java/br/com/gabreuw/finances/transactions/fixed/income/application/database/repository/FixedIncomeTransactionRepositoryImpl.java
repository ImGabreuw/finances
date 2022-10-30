package br.com.gabreuw.finances.transactions.fixed.income.application.database.repository;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.fixed.income.application.database.jpa.FixedIncomeTransactionJPARepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.mapper.FixedIncomeTransactionMapper;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.repository.FixedIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FixedIncomeTransactionRepositoryImpl implements FixedIncomeTransactionRepository {

    private final FixedIncomeTransactionJPARepository jpaRepository;
    private final FixedIncomeTransactionMapper mapper;

    @Override
    public FixedIncomeTransaction save(FixedIncomeTransaction fixedIncomeTransaction) {
        var transactionEntity = mapper.mapToEntity(fixedIncomeTransaction);

        return mapper.mapToDomain(
                jpaRepository.save(transactionEntity)
        );
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public Optional<FixedIncomeTransaction> findById(Long id) {
        return jpaRepository
                .findById(id)
                .map(mapper::mapToDomain);
    }

    @Override
    public List<FixedIncomeTransaction> findAll(PageInfo pageInfo) {
        var transactionEntities = jpaRepository
                .findAll(pageInfo.toPageRequest())
                .toList();

        return mapper.mapListToDomain(transactionEntities);
    }

}
