import express, { Request, Response } from "express";
import { GetLastAnnouncementUseCase } from "./announcement-search-engine/usecases/get-last-announcement-usecase";
import { StatusInvestAnnouncementsGateway } from './gateways/status_invest/status-invest-gateway';
import { BaseError } from "./shared/errors/base-error";
import { HttpStatus } from './shared/web/http-status';

export const routes = express.Router();

routes.get("/announcements/:assetCode", async (request: Request, response: Response) => {
  const { assetCode } = request.params;

  try {
    const statusInvestGateway = new StatusInvestAnnouncementsGateway();

    const useCase = new GetLastAnnouncementUseCase(statusInvestGateway);

    const announcement = await useCase.execute({ assetCode });

    return response.status(HttpStatus.OK).json(announcement)
  } catch (error) {
    console.log(error);

    if (error instanceof BaseError) {
      return response.status(error.getHttpStatusCode()).json({
        message: error.message
      })
    }

    return response.status(HttpStatus.INTERNAL_SERVER_ERROR).json({
      message: `An expected error occurred. Please contact the support team.`
    })
  }

})
