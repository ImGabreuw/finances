package br.com.gabreuw.finances.announcement_search_engine.domain.entities;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus;
import br.com.gabreuw.finances.shared.validation.SelfValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

import static br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus.*;

@NoArgsConstructor
@Builder
@Data
public class Announcement implements SelfValidation<Announcement> {

    private Long id;

    @NotBlank
    private String assetCode;

    @NotBlank
    private String title;

    @NotNull
    private LocalDate releaseDate;

    @NotBlank
    @URL
    private String downloadUrl;

    private NotificationStatus notificationStatus;

    public Announcement(Long id, String assetCode, String title, LocalDate releaseDate, String downloadUrl, NotificationStatus notificationStatus) {
        this.id = id;
        this.assetCode = assetCode;
        this.title = title;
        this.releaseDate = releaseDate;
        this.downloadUrl = downloadUrl;
        this.notificationStatus = notificationStatus == null ? PENDING : notificationStatus;

        validate(this);
    }

    public void notified() {
        notificationStatus = NOTIFIED;
    }

    public void error() {
        notificationStatus = ERROR;
    }

}
