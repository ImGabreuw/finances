package br.com.gabreuw.finances.announcement_search_engine.application.web.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Builder
public record AnnouncementDTO(
        @NotBlank
        String assetCode,

        @NotBlank
        String title,

        @NotNull
        LocalDate releaseDate,

        @NotBlank
        @URL
        String downloadUrl
) {
}
