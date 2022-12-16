package br.com.gabreuw.finances.announcement_search_engine.domain.usecases;

import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.provider.AnnouncementProvider;
import br.com.gabreuw.finances.announcement_search_engine.domain.adapters.repository.AnnouncementRepository;
import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;
import br.com.gabreuw.finances.announcement_search_engine.domain.errors.NotFoundAnnouncementsException;
import br.com.gabreuw.finances.shared.usecase.UseCase;

public record GetLastAnnouncementUseCase(
        AnnouncementRepository announcementRepository,
        AnnouncementProvider announcementProvider
) implements UseCase<GetLastAnnouncementUseCase.InputValues, GetLastAnnouncementUseCase.OutputValues> {

    @Override
    public GetLastAnnouncementUseCase.OutputValues execute(GetLastAnnouncementUseCase.InputValues input) {
        var assetCode = input.assetCode();

        var exists = announcementRepository.existsAnnouncementByAssetCode(assetCode);

        if (exists) {
            var lastAnnouncement = announcementRepository.getLastAnnouncement(assetCode);
            return new OutputValues(lastAnnouncement);
        }

        var lastAnnouncement = announcementProvider
                .getLastAnnouncement(assetCode)
                .orElseThrow(() -> new NotFoundAnnouncementsException(assetCode));

        var savedAnnouncement = announcementRepository.save(lastAnnouncement);

        return new OutputValues(savedAnnouncement);
    }

    public record InputValues(String assetCode) implements UseCase.InputValues {
    }

    public record OutputValues(Announcement announcement) implements UseCase.OutputValues {
    }

}