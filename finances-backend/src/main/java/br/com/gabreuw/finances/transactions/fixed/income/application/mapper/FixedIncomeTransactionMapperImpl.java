package br.com.gabreuw.finances.transactions.fixed.income.application.mapper;

import br.com.gabreuw.finances.transactions.fixed.income.application.database.entities.FixedIncomeTransactionEntity;
import br.com.gabreuw.finances.transactions.fixed.income.application.web.dto.response.TransactionResponse;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.mapper.FixedIncomeTransactionMapper;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.FixedIncomeTransaction;
import org.springframework.stereotype.Component;

@Component
public class FixedIncomeTransactionMapperImpl implements FixedIncomeTransactionMapper {

    @Override
    public FixedIncomeTransaction mapToDomain(FixedIncomeTransactionEntity entity) {
        return FixedIncomeTransaction.builder()
                .id(entity.getId())
                .assetType(entity.getAssetType())
                .issuingAgency(entity.getIssuingAgency())
                .description(entity.getDescription())
                .profitabilityInPercent(entity.getProfitabilityInPercent())
                .indexer(entity.getIndexer())
                .liquidity(entity.getLiquidity())
                .expiresIn(entity.getExpiresIn())
                .totalInvested(entity.getTotalInvested())
                .units(entity.getUnits())
                .operationDate(entity.getOperationDate())
                .build();
    }

    @Override
    public FixedIncomeTransactionEntity mapToEntity(FixedIncomeTransaction domain) {
        return FixedIncomeTransactionEntity.builder()
                .id(domain.getId())
                .assetType(domain.getAssetType())
                .issuingAgency(domain.getIssuingAgency())
                .description(domain.getDescription())
                .profitabilityInPercent(domain.getProfitabilityInPercent())
                .indexer(domain.getIndexer())
                .liquidity(domain.getLiquidity())
                .expiresIn(domain.getExpiresIn())
                .totalInvested(domain.getTotalInvested())
                .units(domain.getUnits())
                .operationDate(domain.getOperationDate())
                .build();
    }

    @Override
    public TransactionResponse mapToResponse(FixedIncomeTransaction domain) {
        return TransactionResponse.builder()
                .id(domain.getId())
                .assetType(domain.getAssetType())
                .issuingAgency(domain.getIssuingAgency())
                .description(domain.getDescription())
                .profitabilityInPercent(domain.getProfitabilityInPercent())
                .indexer(domain.getIndexer())
                .liquidity(domain.getLiquidity())
                .expiresIn(domain.getExpiresIn())
                .totalInvested(domain.getTotalInvested())
                .units(domain.getUnits())
                .operationDate(domain.getOperationDate())
                .build();

    }

}
