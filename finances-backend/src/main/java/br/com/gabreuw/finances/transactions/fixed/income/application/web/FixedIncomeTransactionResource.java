package br.com.gabreuw.finances.transactions.fixed.income.application.web;

import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.fixed.income.application.service.FixedIncomeTransactionService;
import br.com.gabreuw.finances.transactions.fixed.income.application.web.dto.request.SaveTransactionRequest;
import br.com.gabreuw.finances.transactions.fixed.income.application.web.dto.response.TransactionResponse;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.DeleteFixedIncomeTransactionByIdUseCase;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.FindAllFixedIncomeTransactionUseCase;
import br.com.gabreuw.finances.transactions.fixed.income.domain.usecases.SaveFixedIncomeTransactionUseCase;
import br.com.gabreuw.finances.transactions.variable.income.application.web.dto.request.PageInfoRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/api/v1/fixed_income/transaction")
@RequiredArgsConstructor
public class FixedIncomeTransactionResource {

    private final FixedIncomeTransactionService fixedIncomeTransactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> save(@Valid @RequestBody SaveTransactionRequest request) {
        var input = new SaveFixedIncomeTransactionUseCase.InputValues(
                request.assetType(),
                request.issuingAgency(),
                request.description(),
                request.profitabilityInPercent(),
                request.indexer(),
                request.liquidity(),
                request.expiresIn(),
                request.totalInvested(),
                request.units(),
                request.operationDate()
        );

        var transaction = fixedIncomeTransactionService.save(input);

        return ResponseEntity
                .status(CREATED)
                .body(transaction);

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var input = new DeleteFixedIncomeTransactionByIdUseCase.InputValues(id);

        fixedIncomeTransactionService.delete(input);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> findAll(@RequestBody(required = false) PageInfoRequest request) {
        var pageInfo = PageInfo.from(request);

        var input = new FindAllFixedIncomeTransactionUseCase.InputValues(pageInfo);

        var transactions = fixedIncomeTransactionService.findAll(input);

        return ResponseEntity
                .ok()
                .body(transactions);
    }

}
