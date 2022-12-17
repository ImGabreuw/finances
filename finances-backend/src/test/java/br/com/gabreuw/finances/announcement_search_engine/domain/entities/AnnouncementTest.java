package br.com.gabreuw.finances.announcement_search_engine.domain.entities;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus;
import br.com.gabreuw.finances.shared.validation.SelfValidation;
import br.com.gabreuw.finances.utils.BlankSource;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
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
                .downloadUrl("https://www.domain.com/download")
                .build();

        assertThat(underTest.getNotificationStatus()).isEqualTo(PENDING);
    }

    @DisplayName("Should create Announcement with NotificationStatus different of PENDING")
    @EnumSource(value = NotificationStatus.class, names = {"NOTIFIED", "ERROR"})
    @ParameterizedTest
    void shouldCreateAnnouncementWithNotificationStatusDifferentOfNotified(NotificationStatus notificationStatus) {
        var underTest = Announcement.builder()
                .id(1L)
                .assetCode("AGRO3")
                .title("Fato relevante")
                .releaseDate(LocalDate.of(2022, 12, 10))
                .notificationStatus(notificationStatus)
                .downloadUrl("https://www.domain.com/download")
                .build();

        assertThat(underTest.getNotificationStatus()).isNotEqualTo(PENDING);
    }

    @DisplayName("Should create Announcement with error status using error method")
    @Test
    void shouldCreateAnnouncementWithErrorStatusUsingErrorMethod() {
        var underTest = Announcement.builder()
                .id(1L)
                .assetCode("AGRO3")
                .title("Fato relevante")
                .releaseDate(LocalDate.of(2022, 12, 10))
                .downloadUrl("https://www.domain.com/download")
                .build();

        underTest.error();

        assertThat(underTest.getNotificationStatus()).isEqualTo(ERROR);
    }

    @DisplayName("Should create Announcement with notified status using notified method")
    @Test
    void shouldCreateAnnouncementWithNotifiedStatusUsingNotifiedMethod() {
        var underTest = Announcement.builder()
                .id(1L)
                .assetCode("AGRO3")
                .title("Fato relevante")
                .releaseDate(LocalDate.of(2022, 12, 10))
                .downloadUrl("https://www.domain.com/download")
                .build();

        underTest.notified();

        assertThat(underTest.getNotificationStatus()).isEqualTo(NOTIFIED);
    }

    @DisplayName("Should throw ValidationException when create Announcement with blank AssetCode")
    @BlankSource
    @ParameterizedTest
    void shouldThrowValidationExceptionWhenCreateAnnouncementWithBlankAssetCode(String assetCode) {
        ThrowableAssert.ThrowingCallable underTest = () -> Announcement.builder()
                .id(1L)
                .assetCode(assetCode)
                .title("Fato relevante")
                .releaseDate(LocalDate.of(2022, 12, 10))
                .downloadUrl("https://www.domain.com/download")
                .build();

        assertThatThrownBy(underTest)
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessage("The class Announcement have its constraints violated. [Field[fieldName=assetCode, message=must not be blank, value=%s]]".formatted(assetCode));
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
                .downloadUrl("https://www.domain.com/download")
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
                .downloadUrl("https://www.domain.com/download")
                .build();

        assertThatThrownBy(underTest)
                .isInstanceOf(SelfValidation.ValidationException.class)
                .hasMessage("The class Announcement have its constraints violated. [Field[fieldName=releaseDate, message=must not be null, value=%s]]".formatted(releaseDate));
    }

}