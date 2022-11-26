package br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.mapper;

import br.com.gabreuw.finances.transactions.fixed.income.application.database.entities.FixedIncomeTransactionEntity;
import br.com.gabreuw.finances.transactions.fixed.income.application.web.dto.response.TransactionResponse;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;

import java.util.List;
import java.util.stream.Collectors;

public interface FixedIncomeTransactionMapper {

    FixedIncomeTransaction mapToDomain(FixedIncomeTransactionEntity entity);

    default List<FixedIncomeTransaction> mapListToDomain(List<FixedIncomeTransactionEntity> entityList) {
        return entityList
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    FixedIncomeTransactionEntity mapToEntity(FixedIncomeTransaction domain);

    default List<FixedIncomeTransactionEntity> mapListToEntity(List<FixedIncomeTransaction> domainList) {
        return domainList
                .stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

    TransactionResponse mapToResponse(FixedIncomeTransaction domain);

    default List<TransactionResponse> mapListToResponse(List<FixedIncomeTransaction> domainList) {
        return domainList
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

}
