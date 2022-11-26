package br.com.gabreuw.finances.transactions.variable.income.application.web.dto.request;

import br.com.gabreuw.finances.shared.pagination.PageInfo;

import java.io.Serializable;

public record PageInfoRequest(
        int pageNumber,
        int pageSize,
        PageInfo.Order... orders
) implements Serializable {
}
