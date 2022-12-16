package br.com.gabreuw.finances.announcement_search_engine.database.entities;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AnnouncementEntity {

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

}
