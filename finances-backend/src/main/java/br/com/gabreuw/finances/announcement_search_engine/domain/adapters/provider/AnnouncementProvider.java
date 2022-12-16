package br.com.gabreuw.finances.announcement_search_engine.domain.adapters.provider;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;

import java.util.Optional;

public interface AnnouncementProvider {

    Optional<Announcement> getLastAnnouncement(String assetCode);

}
