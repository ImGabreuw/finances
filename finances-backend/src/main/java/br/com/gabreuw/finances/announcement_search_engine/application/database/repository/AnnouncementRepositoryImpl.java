package br.com.gabreuw.finances.announcement_search_engine.application.database.repository;

import br.com.gabreuw.finances.announcement_search_engine.application.database.jpa.AnnouncementJpaRepository;
import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.mapper.AnnouncementMapper;
import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.repository.AnnouncementRepository;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus.*;

@Repository
@RequiredArgsConstructor
public class AnnouncementRepositoryImpl implements AnnouncementRepository {

    private final AnnouncementJpaRepository announcementJpaRepository;
    private final AnnouncementMapper announcementMapper;

    @Override
    public Announcement save(Announcement announcement) {
        var input = announcementMapper.mapToEntity(announcement);

        var savedAnnouncement = announcementJpaRepository.save(input);

        return announcementMapper.mapToDomain(savedAnnouncement);
    }

    @Override
    public boolean existsAnnouncementByAssetCode(String assetCode) {
        return announcementJpaRepository.existsByAssetCode(assetCode);
    }

    @Override
    public Announcement getLastNotifiedAnnouncement(String assetCode) {
        var announcement = announcementJpaRepository
                .getByAssetCodeAndNotificationStatusOrderByReleaseDateDesc(assetCode, NOTIFIED);

        return announcementMapper.mapToDomain(announcement);
    }

    @Override
    public List<Announcement> getUnnotifiedAnnouncements(String assetCode) {
        var announcements = announcementJpaRepository
                .getAllByAssetCodeIgnoreCaseAndNotificationStatusInOrderByReleaseDateDesc(assetCode, PENDING, ERROR);

        return announcementMapper.mapToDomainList(announcements);
    }

    @Override
    public Announcement getLastAnnouncement(String assetCode) {
        var announcement = announcementJpaRepository
                .getByAssetCodeOrderByReleaseDateDesc(assetCode);

        return announcementMapper.mapToDomain(announcement);
    }

}
