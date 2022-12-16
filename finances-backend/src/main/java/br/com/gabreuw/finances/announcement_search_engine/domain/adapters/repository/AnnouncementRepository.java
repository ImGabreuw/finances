package br.com.gabreuw.finances.announcement_search_engine.domain.adapters.repository;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;

import java.util.List;

public interface AnnouncementRepository {

    Announcement save(Announcement announcement);

    boolean existsAnnouncementByAssetCode(String assetCode);

    Announcement getLastNotifiedAnnouncement(String assetCode);

    List<Announcement> getUnnotifiedAnnouncements(String assetCode);

    Announcement getLastAnnouncement(String assetCode);

}
