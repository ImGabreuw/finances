package br.com.gabreuw.finances.announcement_search_engine.domain.adapters.mapper;

import br.com.gabreuw.finances.announcement_search_engine.application.web.dto.AnnouncementDTO;
import br.com.gabreuw.finances.announcement_search_engine.database.entities.AnnouncementEntity;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;

import java.util.List;
import java.util.stream.Collectors;

public interface AnnouncementMapper {

    Announcement mapToDomain(AnnouncementDTO dto);

    Announcement mapToDomain(AnnouncementEntity entity);

    default List<Announcement> mapToDomainList(List<AnnouncementEntity> entities) {
        return entities
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    AnnouncementEntity mapToEntity(Announcement domain);

    AnnouncementDTO mapToDTO(Announcement domain);

}
