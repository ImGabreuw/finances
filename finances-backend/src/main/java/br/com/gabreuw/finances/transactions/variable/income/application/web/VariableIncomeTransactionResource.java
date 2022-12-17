package br.com.gabreuw.finances.transactions.variable.income.application.web;

import br.com.gabreuw.finances.shared.helper.LocalDateHelper;
import br.com.gabreuw.finances.shared.pagination.PageInfo;
import br.com.gabreuw.finances.transactions.variable.income.application.services.VariableIncomeTransactionService;
import br.com.gabreuw.finances.transactions.variable.income.application.web.dto.request.PageInfoRequest;
import br.com.gabreuw.finances.transactions.variable.income.application.web.dto.request.SaveTransactionRequest;
import br.com.gabreuw.finances.transactions.variable.income.application.web.dto.response.TransactionResponse;
import br.com.gabreuw.finances.transactions.variable.income.domain.usecases.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/api/v1/variable_income/transaction")
@RequiredArgsConstructor
public class VariableIncomeTransactionResource {

    private final VariableIncomeTransactionService variableIncomeTransactionService;

    @PostMapping
    public ResponseEntity<TransactionResponse> save(@Valid @RequestBody SaveTransactionRequest request) {
        var input = new SaveVariableIncomeTransactionUseCase.InputValues(
                request.assetCode(),
                request.assetType(),
                request.operationDate(),
                request.operationType(),
                request.operationAverageCost(),
                request.numberOfShares()
        );

        var transaction = variableIncomeTransactionService.save(input);

        return ResponseEntity
                .status(CREATED)
                .body(transaction);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var input = new DeleteVariableIncomeTransactionByIdUseCase.InputValues(id);

        variableIncomeTransactionService.delete(input);

        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> findAll(@RequestBody(required = false) PageInfoRequest request) {
        var pageInfo = PageInfo.from(request);

        var input = new FindAllVariableIncomeTransactionUseCase.InputValues(pageInfo);

        var transactions = variableIncomeTransactionService.findAll(input);

        return ResponseEntity
                .ok()
                .body(transactions);
    }

    @GetMapping(path = "/{assetCode}")
    public ResponseEntity<List<TransactionResponse>> findAllByAssetCode(
            @PathVariable String assetCode,
            @RequestBody(required = false) PageInfoRequest request
    ) {
        var pageInfo = PageInfo.from(request);

        var input = new FindAllVariableIncomeTransactionByAssetCodeUseCase.InputValues(assetCode, pageInfo);

        var transactions = variableIncomeTransactionService.findAllByAssetCode(input);

        return ResponseEntity
                .ok()
                .body(transactions);
    }

    @GetMapping(path = "/assetType/{assetType}")
    public ResponseEntity<List<TransactionResponse>> findAllByAssetType(
            @PathVariable String assetType,
            @RequestBody(required = false) PageInfoRequest request
    ) {
        var pageInfo = PageInfo.from(request);

        var input = new FindAllVariableIncomeTransactionByAssetTypeUseCase.InputValues(assetType, pageInfo);

        var transactions = variableIncomeTransactionService.findAllByAssetType(input);

        return ResponseEntity
                .ok()
                .body(transactions);
    }

    @GetMapping(path = "/operationDate/{operationDate}")
    public ResponseEntity<List<TransactionResponse>> findAllByOperationDate(
            @PathVariable String operationDate,
            @RequestBody(required = false) PageInfoRequest request
    ) {
        var pageInfo = PageInfo.from(request);

        var input = new FindAllVariableIncomeTransactionByOperationDateUseCase.InputValues(
                LocalDateHelper.parse(operationDate),
                pageInfo
        );

        var output = variableIncomeTransactionService.findAllByOperationDate(input);

        return ResponseEntity
                .ok()
                .body(output);
    }

}
