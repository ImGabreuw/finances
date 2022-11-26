package br.com.gabreuw.finances.transactions.variable.income.application.database.repository;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.application.database.jpa.VariableIncomeTransactionJPARepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.mapper.VariableIncomeTransactionMapper;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository.VariableIncomeTransactionRepository;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VariableIncomeTransactionRepositoryImpl implements VariableIncomeTransactionRepository {

    private final VariableIncomeTransactionJPARepository jpaRepository;
    private final VariableIncomeTransactionMapper mapper;

    @Override
    public VariableIncomeTransaction save(VariableIncomeTransaction variableIncomeTransaction) {
        var transactionEntity = mapper.mapToEntity(variableIncomeTransaction);

        return mapper.mapToDomain(
                jpaRepository.save(transactionEntity)
        );
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
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
    public Optional<VariableIncomeTransaction> findById(Long id) {
        return jpaRepository
                .findById(id)
                .map(mapper::mapToDomain);
    }

    @Override
    public List<VariableIncomeTransaction> findAll() {
        return jpaRepository
                .findAll()
                .stream()
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public List<VariableIncomeTransaction> findAll(PageInfo pageInfo) {
        var pageRequest = pageInfo.toPageRequest();

        return jpaRepository
                .findAll(pageRequest)
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public List<VariableIncomeTransaction> findAllByAssetCode(String assetCode) {
        return jpaRepository
                .findAllByAssetCodeIgnoreCase(assetCode)
                .stream()
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public List<VariableIncomeTransaction> findAllByAssetCode(String assetCode, PageInfo pageInfo) {
        var pageRequest = pageInfo.toPageRequest();

        return jpaRepository
                .findAllByAssetCodeIgnoreCase(assetCode, pageRequest)
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public List<VariableIncomeTransaction> findAllByAssetType(AssetType assetType) {
        return jpaRepository
                .findAllByAssetType(assetType)
                .stream()
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public List<VariableIncomeTransaction> findAllByAssetType(AssetType assetType, PageInfo pageInfo) {
        var pageRequest = pageInfo.toPageRequest();

        return jpaRepository
                .findAllByAssetType(assetType, pageRequest)
                .map(mapper::mapToDomain)
                .toList();
    }

    @Override
    public List<VariableIncomeTransaction> findAllByOperationDate(LocalDate operationDate, PageInfo pageInfo) {
        var pageRequest = pageInfo.toPageRequest();

        return jpaRepository
                .findAllByOperationDate(operationDate, pageRequest)
                .map(mapper::mapToDomain)
                .toList();
    }
}
