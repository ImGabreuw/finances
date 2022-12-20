package br.com.gabreuw.finances.announcement_search_engine.domain.usecases;

import br.com.gabreuw.finances.announcement_search_engine.domain.entities.Announcement;
import br.com.gabreuw.finances.investment_portfolio.domain.entities.VariableIncomeAsset;
import br.com.gabreuw.finances.investment_portfolio.domain.usecases.CalculateVariableIncomePositionUseCase;
import br.com.gabreuw.finances.shared.usecase.UseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public record NotifyUserAssetsAnnouncementsUseCase(
        CalculateVariableIncomePositionUseCase calculateVariableIncomePositionUseCase,
        NotifyLastAnnouncementUseCase notifyLastAnnouncementUseCase
) implements UseCase<NotifyUserAssetsAnnouncementsUseCase.InputValues, NotifyUserAssetsAnnouncementsUseCase.OutputValues> {

    @Override
    public OutputValues execute(InputValues input) {
        var calculateVariableIncomeInput = new CalculateVariableIncomePositionUseCase.InputValues();
        var calculateVariableIncomeOutput = calculateVariableIncomePositionUseCase.execute(calculateVariableIncomeInput);

        var investmentProfileAssetsCode = calculateVariableIncomeOutput
                .assets()
                .stream()
                .map(VariableIncomeAsset::getCode);

        var announcements = investmentProfileAssetsCode
                .map(NotifyLastAnnouncementUseCase.InputValues::new)
                .map(notifyLastAnnouncementUseCase::execute)
                .map(NotifyLastAnnouncementUseCase.OutputValues::announcement)
                .toList();

        return new OutputValues(announcements);
    }

    public record InputValues() implements UseCase.InputValues {
    }

    public record OutputValues(List<Announcement> announcements) implements UseCase.OutputValues {
    }

}