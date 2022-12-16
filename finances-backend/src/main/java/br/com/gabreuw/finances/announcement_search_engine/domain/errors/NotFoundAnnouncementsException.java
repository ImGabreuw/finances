package br.com.gabreuw.finances.announcement_search_engine.domain.errors;

import br.com.gabreuw.finances.shared.errors.BaseException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class NotFoundAnnouncementsException extends BaseException {

    public NotFoundAnnouncementsException(String assetCode) {
        super(NOT_FOUND, "Não há nenhum comunicado de %s", assetCode);
    }

}
