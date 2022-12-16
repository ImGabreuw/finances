package br.com.gabreuw.finances.announcement_search_engine.application.mapper;

import br.com.gabreuw.finances.announcement_search_engine.application.web.dto.AnnouncementDTO;
import br.com.gabreuw.finances.announcement_search_engine.database.entities.AnnouncementEntity;
import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.mapper.AnnouncementMapper;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementMapperImpl implements AnnouncementMapper {

    @Override
    public Announcement mapToDomain(AnnouncementDTO dto) {
        return Announcement.builder()
                .assetCode(dto.assetCode())
                .title(dto.title())
                .releaseDate(dto.releaseDate())
                .downloadUrl(dto.downloadUrl())
                .build();
    }

    @Override
    public Announcement mapToDomain(AnnouncementEntity entity) {
        return Announcement.builder()
                .assetCode(entity.getAssetCode())
                .title(entity.getTitle())
                .releaseDate(entity.getReleaseDate())
                .downloadUrl(entity.getDownloadUrl())
                .build();
    }

    @Override
    public AnnouncementEntity mapToEntity(Announcement domain) {
        return AnnouncementEntity.builder()
                .assetCode(domain.getAssetCode())
                .title(domain.getTitle())
                .releaseDate(domain.getReleaseDate())
                .downloadUrl(domain.getDownloadUrl())
                .build();
    }

    @Override
    public AnnouncementDTO mapToDTO(Announcement domain) {
        return AnnouncementDTO.builder()
                .assetCode(domain.getAssetCode())
                .title(domain.getTitle())
                .releaseDate(domain.getReleaseDate())
                .downloadUrl(domain.getDownloadUrl())
                .build();
    }

}
