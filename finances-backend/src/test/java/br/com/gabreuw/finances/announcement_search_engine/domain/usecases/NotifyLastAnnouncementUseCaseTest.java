package br.com.gabreuw.finances.announcement_search_engine.domain.usecases;

import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.provider.AnnouncementProvider;
import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.repository.AnnouncementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class NotifyLastAnnouncementUseCaseTest {

    private NotifyLastAnnouncementUseCase underTest;

    @Autowired
    private AnnouncementProvider announcementProvider;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @BeforeEach
    void setUp() {
        underTest = new NotifyLastAnnouncementUseCase(announcementRepository, announcementProvider);
    }

    @DisplayName("Should get last announcement of AGRO3 from Status Invest")
    @Test
    void shouldGetLastAnnouncementOfAgro3FromStatusInvest() {
        var assetCode = "AGRO3";
        var input = new NotifyLastAnnouncementUseCase.InputValues(assetCode);

        var output = underTest.execute(input);

        assertThat(output).hasNoNullFieldsOrProperties();
    }

}