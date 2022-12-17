package br.com.gabreuw.finances.announcement_search_engine.application.database.entities;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus;
import br.com.gabreuw.finances.shared.validation.SelfValidation;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

import static br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus.PENDING;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Builder
@Data
public class AnnouncementEntity implements SelfValidation<AnnouncementEntity> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
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

    @Enumerated(STRING)
    private NotificationStatus notificationStatus;

    public AnnouncementEntity(Long id, String assetCode, String title, LocalDate releaseDate, String downloadUrl, NotificationStatus notificationStatus) {
        this.id = id;
        this.assetCode = assetCode;
        this.title = title;
        this.releaseDate = releaseDate;
        this.downloadUrl = downloadUrl;
        this.notificationStatus = notificationStatus == null ? PENDING : notificationStatus;

        validate(this);
    }
}
