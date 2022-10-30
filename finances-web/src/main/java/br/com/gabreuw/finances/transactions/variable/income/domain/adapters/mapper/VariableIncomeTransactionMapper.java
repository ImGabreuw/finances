package br.com.gabreuw.finances.transactions.variable.income.domain.adapters.mapper;

import br.com.gabreuw.finances.transactions.variable.income.application.database.entities.VariableIncomeTransactionEntity;
import br.com.gabreuw.finances.transactions.variable.income.application.web.dto.response.TransactionResponse;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;

import java.util.List;
import java.util.stream.Collectors;

public interface VariableIncomeTransactionMapper {

    VariableIncomeTransaction mapToDomain(VariableIncomeTransactionEntity entity);

    default List<VariableIncomeTransaction> mapListToDomain(List<VariableIncomeTransactionEntity> entityList) {
        return entityList
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    VariableIncomeTransactionEntity mapToEntity(VariableIncomeTransaction domain);

    default List<VariableIncomeTransactionEntity> mapListToEntity(List<VariableIncomeTransaction> domainList) {
        return domainList
                .stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

    TransactionResponse mapToResponse(VariableIncomeTransaction domain);

    default List<TransactionResponse> mapListToResponse(List<VariableIncomeTransaction> domainList) {
        return domainList
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

}
