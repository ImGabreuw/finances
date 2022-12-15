import { StatusInvestAnnouncementsGateway } from '../../gateways/status_invest/status-invest-gateway';
import { RequiredUseCaseInputError } from '../../shared/errors/required-usecase-input-error';
import { Announcement } from '../domain/announcement';

export class GetLastAnnouncementUseCase {
  constructor(private readonly statusInvestGateway: StatusInvestAnnouncementsGateway) { }

  async execute(input: GetLastAnnouncementUseCaseInput): Promise<Announcement> {
    const assetCode = input.assetCode;

    if (!assetCode) {
      throw new RequiredUseCaseInputError("assetCode")
    }

    return await this.statusInvestGateway.getLastAnnouncement(assetCode);
  }
}

type GetLastAnnouncementUseCaseInput = {
  assetCode: string
}
