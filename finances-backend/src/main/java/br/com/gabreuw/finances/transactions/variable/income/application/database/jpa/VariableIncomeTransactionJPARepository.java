package br.com.gabreuw.finances.transactions.variable.income.application.database.jpa;

import br.com.gabreuw.finances.transactions.variable.income.application.database.entities.VariableIncomeTransactionEntity;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VariableIncomeTransactionJPARepository extends JpaRepository<VariableIncomeTransactionEntity, Long> {

    List<VariableIncomeTransactionEntity> findAllByAssetCodeIgnoreCase(String assetCode);

    Page<VariableIncomeTransactionEntity> findAllByAssetCodeIgnoreCase(String assetCode, Pageable pageable);

    List<VariableIncomeTransactionEntity> findAllByAssetType(AssetType assetType);

    Page<VariableIncomeTransactionEntity> findAllByAssetType(AssetType assetType, Pageable pageable);

    Page<VariableIncomeTransactionEntity> findAllByOperationDate(LocalDate operationDate, Pageable pageable);

}
