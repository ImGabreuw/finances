package br.com.gabreuw.finances.announcement_search_engine.application.database.jpa;

import br.com.gabreuw.finances.announcement_search_engine.application.database.entities.AnnouncementEntity;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementJpaRepository extends JpaRepository<AnnouncementEntity, Long> {

    boolean existsByAssetCode(String assetCode);

    AnnouncementEntity getByAssetCodeAndNotificationStatusOrderByReleaseDateDesc(String assetCode, NotificationStatus notificationStatus);

    List<AnnouncementEntity> getAllByAssetCodeIgnoreCaseAndNotificationStatusInOrderByReleaseDateDesc(String assetCode, NotificationStatus... notificationStatus);

    AnnouncementEntity getByAssetCodeOrderByReleaseDateDesc(String assetCode);

}
