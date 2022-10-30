package br.com.gabreuw.finances.transactions.variable.income.application.mapper;

import br.com.gabreuw.finances.transactions.variable.income.application.database.entities.VariableIncomeTransactionEntity;
import br.com.gabreuw.finances.transactions.variable.income.application.web.dto.response.TransactionResponse;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.mapper.VariableIncomeTransactionMapper;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.VariableIncomeTransaction;
import org.springframework.stereotype.Component;

@Component
public class VariableIncomeTransactionMapperImpl implements VariableIncomeTransactionMapper {

    @Override
    public VariableIncomeTransaction mapToDomain(VariableIncomeTransactionEntity entity) {
        return new VariableIncomeTransaction(
                entity.getId(),
                entity.getAssetCode(),
                entity.getAssetType(),
                entity.getOperationDate(),
                entity.getOperationType(),
                entity.getOperationAverageCost(),
                entity.getNumberOfShares(),
                entity.getTotalInvested()
        );
    }

    @Override
    public VariableIncomeTransactionEntity mapToEntity(VariableIncomeTransaction domain) {
        return new VariableIncomeTransactionEntity(
                domain.getId(),
                domain.getAssetCode(),
                domain.getAssetType(),
                domain.getOperationDate(),
                domain.getOperationType(),
                domain.getOperationAverageCost(),
                domain.getNumberOfShares(),
                domain.getTotalInvested()
        );
    }

    @Override
    public TransactionResponse mapToResponse(VariableIncomeTransaction domain) {
        return new TransactionResponse(
                domain.getId(),
                domain.getAssetCode(),
                domain.getAssetType(),
                domain.getOperationDate(),
                domain.getOperationType(),
                domain.getOperationAverageCost(),
                domain.getNumberOfShares(),
                domain.getTotalInvested()
        );
    }

}
