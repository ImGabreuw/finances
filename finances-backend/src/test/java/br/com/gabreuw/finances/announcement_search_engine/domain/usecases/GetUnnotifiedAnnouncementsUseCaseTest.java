package br.com.gabreuw.finances.announcement_search_engine.domain.usecases;

import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.repository.AnnouncementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GetUnnotifiedAnnouncementsUseCaseTest {

    private GetUnnotifiedAnnouncementsUseCase underTest;

    @Autowired
    private AnnouncementRepository announcementRepository;

    @BeforeEach
    void setUp() {
        underTest = new GetUnnotifiedAnnouncementsUseCase(announcementRepository);
    }

    @DisplayName("Should get all unnotified announcements of VALE3")
    @Test
    void shouldGetAllUnnotifiedAnnouncementsOfVALE3() {
        var assetCode = "VALE3";
        var input = new GetUnnotifiedAnnouncementsUseCase.InputValues(assetCode);

        var output = underTest.execute(input);

        System.out.println(output);
    }

}