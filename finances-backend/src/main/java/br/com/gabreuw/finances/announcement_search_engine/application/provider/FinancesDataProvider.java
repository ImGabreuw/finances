package br.com.gabreuw.finances.announcement_search_engine.application.provider;

import br.com.gabreuw.finances.announcement_search_engine.application.web.dto.AnnouncementDTO;
import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.mapper.AnnouncementMapper;
import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.provider.AnnouncementProvider;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;
import br.com.gabreuw.finances.announcement_search_engine.domain.errors.MissingEnviromentVariableException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
public class FinancesDataProvider implements AnnouncementProvider {

    private final Environment env;

    private final RestTemplate restTemplate;
    private final AnnouncementMapper announcementMapper;

    @Override
    public Optional<Announcement> getLastAnnouncement(String assetCode) {
        HttpEntity<String> request = new HttpEntity<>(assetCode);

        var enviromentVariable = "finances-data-provider.endpoints.announcements";
        var announcementEndpoint = env.getProperty(enviromentVariable);

        if (announcementEndpoint == null) {
            throw new MissingEnviromentVariableException(enviromentVariable);
        }

        ResponseEntity<AnnouncementDTO> response = restTemplate.exchange(announcementEndpoint + "/" + assetCode, GET, request, AnnouncementDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            var announcement = announcementMapper.mapToDomain(response.getBody());
            return Optional.of(announcement);
        }

        return Optional.empty();
    }

}
