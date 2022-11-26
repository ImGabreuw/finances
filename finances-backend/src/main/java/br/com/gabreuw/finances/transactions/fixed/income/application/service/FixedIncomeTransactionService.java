package br.com.gabreuw.finances.transactions.fixed.income.application.service;

import br.com.gabreuw.finances.transactions.fixed.income.application.web.dto.response.TransactionResponse;
import br.com.gabreuw.finances.transactions.fixed.income.domain.adapters.mapper.FixedIncomeTransactionMapper;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.DeleteFixedIncomeTransactionByIdUseCase;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.FindAllFixedIncomeTransactionUseCase;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.FixedIncomeTransactionUseCaseExecutor;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.SaveFixedIncomeTransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FixedIncomeTransactionService {

    private final FixedIncomeTransactionUseCaseExecutor useCaseExecutor;
    private final FixedIncomeTransactionMapper mapper;

    private final SaveFixedIncomeTransactionUseCase saveFixedIncomeTransactionUseCase;
    private final DeleteFixedIncomeTransactionByIdUseCase deleteFixedIncomeTransactionByIdUseCase;
    private final FindAllFixedIncomeTransactionUseCase findAllFixedIncomeTransactionUseCase;

    public TransactionResponse save(SaveFixedIncomeTransactionUseCase.InputValues input) {
        return useCaseExecutor.execute(
                saveFixedIncomeTransactionUseCase,
                input,
                outputValues -> mapper.mapToResponse(outputValues.fixedIncomeTransaction())
        );
    }

    public void delete(DeleteFixedIncomeTransactionByIdUseCase.InputValues input) {
        useCaseExecutor.execute(
                deleteFixedIncomeTransactionByIdUseCase,
                input,
                outputValues -> outputValues
        );
    }

    public List<TransactionResponse> findAll(FindAllFixedIncomeTransactionUseCase.InputValues input) {
        return useCaseExecutor.execute(
                findAllFixedIncomeTransactionUseCase,
                input,
                outputValues -> mapper.mapListToResponse(outputValues.fixedIncomeTransactions())
        );
    }

}
