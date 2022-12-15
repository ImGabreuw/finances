import express, { Request, Response } from "express";
import { GetLastAnnouncementUseCase } from "./announcement-search-engine/usecases/get-last-announcement-usecase";
import { StatusInvestAnnouncementsGateway } from "./gateways/status_invest/status-invest-gateway";
import { PuppeteerLauncher } from "./puppeteer-launcher";
import { RealStateFundFacade } from "./real-state-fund/facade/real-state-fund-facade";
import { BaseError } from "./shared/errors/base-error";
import { HttpStatus } from "./shared/web/http-status";
import { StockFacade } from "./stocks/facade/stock-facade";

export const routes = express.Router();

routes.get(
 "/announcements/:assetCode",
 async (request: Request, response: Response) => {
  const { assetCode } = request.params;

  try {
   const statusInvestGateway = new StatusInvestAnnouncementsGateway();

   const useCase = new GetLastAnnouncementUseCase(statusInvestGateway);

   const announcement = await useCase.execute({ assetCode });

   return response.status(HttpStatus.OK).json(announcement);
  } catch (error) {
   console.log(error);

   if (error instanceof BaseError) {
    return response.status(error.getHttpStatusCode()).json({
     message: error.message,
    });
   }

   return response.status(HttpStatus.INTERNAL_SERVER_ERROR).json({
    message: `An expected error occurred. Please contact the support team.`,
   });
  }
 },
);

routes.get(
 "/real-state-fund/:assetCode",
 async (request: Request, response: Response) => {
  const { assetCode } = request.params;
  const { browser, page } = await PuppeteerLauncher.launch();

  try {
   const result = await new RealStateFundFacade(page).searchAndExtractData(
    assetCode,
   );

   return response.json(result);
  } catch ({ errorMessage }) {
   return response.status(400).json({
    error: errorMessage,
   });
  } finally {
   await browser.close();
  }
 },
);

routes.get(
 "/stock/:assetCode",
 async (request: Request, response: Response) => {
  const { assetCode } = request.params;
  const { browser, page } = await PuppeteerLauncher.launch();

  try {
   const result = await new StockFacade(page).searchAndExtractData(assetCode);

   return response.json(result);
  } catch ({ errorMessage }) {
   return response.status(400).json({
    error: errorMessage,
   });
  } finally {
   await browser.close();
  }
 },
);
