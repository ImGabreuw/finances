package br.com.gabreuw.finances.announcement_search_engine.application.web.dto;

import br.com.gabreuw.finances.shared.validation.SelfValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;

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
) implements SelfValidation<AnnouncementDTO> {

    public AnnouncementDTO(String assetCode, String title, LocalDate releaseDate, String downloadUrl) {
        this.assetCode = assetCode;
        this.title = title;
        this.releaseDate = releaseDate;
        this.downloadUrl = downloadUrl;

        validate(this);
    }

}
