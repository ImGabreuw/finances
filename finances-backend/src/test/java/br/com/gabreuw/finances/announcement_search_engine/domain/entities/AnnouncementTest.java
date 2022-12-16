package br.com.gabreuw.finances.announcement_search_engine.domain.entities;

import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.utils.BlankSource;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.time.LocalDate;

import static br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnnouncementTest {

    @DisplayName("Should create Announcenment")
    @Test
    void shouldCreateAnnouncenment() {
        var underTest = Announcement.builder()
                .id(1L)
                .assetCode("AGRO3")
                .title("Fato relevante")
                .releaseDate(LocalDate.of(2022, 12, 10))
                .build();

        assertThat(underTest.getNotificationStatus()).isEqualTo(PENDING);
    }

    @DisplayName("Should create Announcenment with notified status")
    @Test
    void shouldCreateAnnouncenmentWithNotifiedStatus() {
        var underTest = Announcement.builder()
                .id(1L)
                .assetCode("AGRO3")
                .title("Fato relevante")
                .releaseDate(LocalDate.of(2022, 12, 10))
                .notificationStatus(NOTIFIED)
                .build();

        assertThat(underTest.getNotificationStatus()).isEqualTo(NOTIFIED);
    }

    @DisplayName("Should create Announcement with error status")
    @Test
    void shouldCreateAnnouncementWithErrorStatus() {
        var underTest = Announcement.builder()
                .id(1L)
                .assetCode("AGRO3")
                .title("Fato relevante")
                .releaseDate(LocalDate.of(2022, 12, 10))
                .notificationStatus(ERROR)
                .build();

        assertThat(underTest.getNotificationStatus()).isEqualTo(ERROR);
    }

    @DisplayName("Should throw ValidationException when create Announcement with blank EnterpriseCode")
    @BlankSource
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateAnnouncementWithBlankEnterpriseCode(String enterpriseCode) {
        ThrowableAssert.ThrowingCallable underTest = () -> Announcement.builder()
                .id(1L)
                .assetCode(enterpriseCode)
                .title("Fato relevante")
                .releaseDate(LocalDate.of(2022, 12, 10))
                .build();

        assertThatThrownBy(underTest)
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessage("The class Announcement have its constraints violated. [Field[fieldName=assetCode, message=must not be blank, value=%s]]".formatted(enterpriseCode));
    }

    @DisplayName("Should throw ValidationException when create Announcement with blank Title")
    @BlankSource
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateAnnouncementWithBlankTitle(String title) {
        ThrowableAssert.ThrowingCallable underTest = () -> Announcement.builder()
                .id(1L)
                .assetCode("AGRO3")
                .title(title)
                .releaseDate(LocalDate.of(2022, 12, 10))
                .build();

        assertThatThrownBy(underTest)
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessage("The class Announcement have its constraints violated. [Field[fieldName=title, message=must not be blank, value=%s]]".formatted(title));
    }

    @DisplayName("Should throw ValidationException when create Announcement with null ReleaseDate")
    @NullSource
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateAnnouncementWithNullReleaseDate(LocalDate releaseDate) {
        ThrowableAssert.ThrowingCallable underTest = () -> Announcement.builder()
                .id(1L)
                .assetCode("AGRO3")
                .title("Fato relevante")
                .releaseDate(releaseDate)
                .build();

        assertThatThrownBy(underTest)
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessage("The class Announcement have its constraints violated. [Field[fieldName=releaseDate, message=must not be null, value=%s]]".formatted(releaseDate));
    }

}