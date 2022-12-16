package br.com.gabreuw.finances.shared.helper;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnumHelperTest {

    @DisplayName("Should provide a random NotificationStatus")
    @Test
    void shouldProvideARandomNotificationStatus() {
        var underTest = EnumHelper.randomValue(NotificationStatus.class);

        assertThat(underTest).isNotNull();
    }


}