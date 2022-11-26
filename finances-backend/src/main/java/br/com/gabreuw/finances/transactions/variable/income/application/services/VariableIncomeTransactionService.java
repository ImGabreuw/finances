package br.com.gabreuw.finances.transactions.variable.income.application.services;

import br.com.gabreuw.finances.transactions.variable.income.application.web.dto.response.TransactionResponse;
import br.com.gabreuw.finances.transactions.variable.income.domain.adapters.mapper.VariableIncomeTransactionMapper;
import br.com.gabreuw.finances.transactions.variable.income.domain.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VariableIncomeTransactionService {

    private final VariableIncomeTransactionUseCaseExecutor useCaseExecutor;
    private final VariableIncomeTransactionMapper mapper;

    private final SaveVariableIncomeTransactionUseCase saveVariableIncomeTransactionUseCase;
    private final DeleteVariableIncomeTransactionByIdUseCase deleteVariableIncomeTransactionByIdUseCase;
    private final FindAllVariableIncomeTransactionUseCase findAllVariableIncomeTransactionUseCase;
    private final FindAllVariableIncomeTransactionByAssetCodeUseCase findAllVariableIncomeTransactionByAssetCodeUseCase;
    private final FindAllVariableIncomeTransactionByAssetTypeUseCase findAllVariableIncomeTransactionByAssetTypeUseCase;
    private final FindAllVariableIncomeTransactionByOperationDateUseCase findAllVariableIncomeTransactionByOperationDateUseCase;

    public TransactionResponse save(SaveVariableIncomeTransactionUseCase.InputValues input) {
        return useCaseExecutor.execute(
                saveVariableIncomeTransactionUseCase,
                input,
                output -> mapper.mapToResponse(output.variableIncomeTransaction())
        );
    }

    public void delete(DeleteVariableIncomeTransactionByIdUseCase.InputValues input) {
        useCaseExecutor.execute(
                deleteVariableIncomeTransactionByIdUseCase,
                input,
                outputValues -> outputValues
        );
    }

    public List<TransactionResponse> findAll(FindAllVariableIncomeTransactionUseCase.InputValues input) {
        return useCaseExecutor.execute(
                findAllVariableIncomeTransactionUseCase,
                input,
                outputValues -> mapper.mapListToResponse(outputValues.variableIncomeTransactions())
        );
    }

    public List<TransactionResponse> findAllByAssetCode(FindAllVariableIncomeTransactionByAssetCodeUseCase.InputValues input) {
        return useCaseExecutor.execute(
                findAllVariableIncomeTransactionByAssetCodeUseCase,
                input,
                outputValues -> mapper.mapListToResponse(outputValues.variableIncomeTransactions())
        );
    }

    public List<TransactionResponse> findAllByAssetType(FindAllVariableIncomeTransactionByAssetTypeUseCase.InputValues  input) {
        return useCaseExecutor.execute(
                findAllVariableIncomeTransactionByAssetTypeUseCase,
                input,
                outputValues -> mapper.mapListToResponse(outputValues.variableIncomeTransactions())
        );
    }

    public List<TransactionResponse> findAllByOperationDate(FindAllVariableIncomeTransactionByOperationDateUseCase.InputValues input) {
        return useCaseExecutor.execute(
                findAllVariableIncomeTransactionByOperationDateUseCase,
                input,
                outputValues -> mapper.mapListToResponse(outputValues.variableIncomeTransactions())
        );
    }

}
