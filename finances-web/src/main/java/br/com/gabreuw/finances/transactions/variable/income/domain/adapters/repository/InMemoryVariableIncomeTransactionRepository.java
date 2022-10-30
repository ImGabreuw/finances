package br.com.gabreuw.finances.transactions.variable.income.domain.adapters.repository;

import br.com.gabreuw.finances.shared.helper.VariableIncomeTransactionHelper;
import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryVariableIncomeTransactionRepository implements VariableIncomeTransactionRepository {

    private final Map<Long, VariableIncomeTransaction> database = new ConcurrentHashMap<>();

    public InMemoryVariableIncomeTransactionRepository() {
        var pageInfo = PageInfo.createDefault();

        var transactionHelper = VariableIncomeTransactionHelper.getINSTANCE();

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
    public VariableIncomeTransaction save(VariableIncomeTransaction variableIncomeTransaction) {
        var id = getNextId();

        if (variableIncomeTransaction.getId() == null) {
            variableIncomeTransaction.setId(id);
        }

        database.put(id, variableIncomeTransaction);

        return variableIncomeTransaction;
    }

    @Override
    public void deleteAll() {
        database.clear();
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
    public Optional<VariableIncomeTransaction> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<VariableIncomeTransaction> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public List<VariableIncomeTransaction> findAll(PageInfo pageInfo) {
        return new ArrayList<>(database.values())
                .subList(pageInfo.getFirstElementPosition(), pageInfo.getLastElementPosition() + 1);
    }

    @Override
    public List<VariableIncomeTransaction> findAllByAssetCode(String assetCode) {
        return database.values()
                .stream()
                .filter(transaction -> transaction.getAssetCode().equalsIgnoreCase(assetCode))
                .collect(Collectors.toList());
    }

    @Override
    public List<VariableIncomeTransaction> findAllByAssetCode(String assetCode, PageInfo pageInfo) {
        return database.values()
                .stream()
                .filter(transaction -> transaction.getAssetCode().equalsIgnoreCase(assetCode))
                .limit(pageInfo.getPageSize())
                .collect(Collectors.toList());
    }

    @Override
    public List<VariableIncomeTransaction> findAllByAssetType(AssetType assetType) {
        return database.values()
                .stream()
                .filter(transaction -> transaction.getAssetType() == assetType)
                .collect(Collectors.toList());
    }

    @Override
    public List<VariableIncomeTransaction> findAllByAssetType(AssetType assetType, PageInfo pageInfo) {
        return database.values()
                .stream()
                .filter(transaction -> transaction.getAssetType() == assetType)
                .limit(pageInfo.getPageSize())
                .collect(Collectors.toList());
    }

    @Override
    public List<VariableIncomeTransaction> findAllByOperationDate(LocalDate operationDate, PageInfo pageInfo) {
        return database.values()
                .stream()
                .filter(transaction -> transaction.isOperationDateEqualsOrAfter(operationDate))
                .limit(pageInfo.getPageSize())
                .collect(Collectors.toList());
    }

}
