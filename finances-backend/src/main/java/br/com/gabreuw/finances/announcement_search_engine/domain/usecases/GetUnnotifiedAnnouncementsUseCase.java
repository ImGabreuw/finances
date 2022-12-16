package br.com.gabreuw.finances.announcement_search_engine.domain.usecases;

import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.repository.AnnouncementRepository;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record GetUnnotifiedAnnouncementsUseCase(
        AnnouncementRepository announcementRepository
) implements UseCase<GetUnnotifiedAnnouncementsUseCase.InputValues, GetUnnotifiedAnnouncementsUseCase.OutputValues> {

    @Override
    public OutputValues execute(InputValues input) {
        var assetCode = input.assetCode();

        var unnotifiedAnnouncements = announcementRepository.getUnnotifiedAnnouncements(assetCode);

        return new OutputValues(unnotifiedAnnouncements);
    }

    public record InputValues(String assetCode) implements UseCase.InputValues {
    }

    public record OutputValues(List<Announcement> announcements) implements UseCase.OutputValues {
    }

}