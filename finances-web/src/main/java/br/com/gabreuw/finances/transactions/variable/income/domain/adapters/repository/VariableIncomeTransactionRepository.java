package br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VariableIncomeTransactionRepository {

    VariableIncomeTransaction save(VariableIncomeTransaction variableIncomeTransaction);

    void deleteAll();

    void deleteById(Long id);

    boolean existsById(Long id);

    Optional<VariableIncomeTransaction> findById(Long id);

    List<VariableIncomeTransaction> findAll();

    List<VariableIncomeTransaction> findAll(PageInfo pageInfo);

    List<VariableIncomeTransaction> findAllByAssetCode(String assetCode);

    List<VariableIncomeTransaction> findAllByAssetCode(String assetCode, PageInfo pageInfo);

    List<VariableIncomeTransaction> findAllByAssetType(AssetType assetType);

    List<VariableIncomeTransaction> findAllByAssetType(AssetType assetType, PageInfo pageInfo);

    List<VariableIncomeTransaction> findAllByOperationDate(LocalDate operationDate, PageInfo pageInfo);

}
