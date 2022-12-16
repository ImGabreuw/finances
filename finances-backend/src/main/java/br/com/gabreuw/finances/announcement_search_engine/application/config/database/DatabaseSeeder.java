package br.com.gabreuw.finances.announcement_search_engine.application.config.database;

import br.com.gabreuw.finances.announcement_search_engine.database.jpa.AnnouncementJpaRepository;
import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.repository.AnnouncementRepository;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.helper.AnnouncementHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"test", "dev"})
@Configuration
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementJpaRepository announcementJpaRepository;

    @Override
    public void run(String... args) {
        AnnouncementHelper
                .createFakeAnnouncements(9)
                .forEach(announcementRepository::save);

        announcementJpaRepository.findAll().forEach(System.out::println);
    }

}
