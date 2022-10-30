import { Page } from "puppeteer";
import { FundamentusStockDTO } from "../gateway/dto/fundamentus-stock-dto.js";
import { StatusInvestStockDTO } from "../gateway/dto/status-invest-stock-dto.js";
import { FundamentusStockAPI } from "../gateway/fundamentus-stock-api.js";
import { StatusInvestStockAPI } from "../gateway/status-invest-stock-api.js";
import { FundamentusStepsService } from "../service/fundamentus-steps-service.js";
import { FundamentusXPathService } from "../service/fundamentus-xpath-service.js";
import { StatusInvestStepsService } from "../service/status-invest-steps-service.js";
import { StatusInvestXPathService } from "../service/status-invest-xpath-service.js";

class StockFacade {
  private statusInvestStockAPI: StatusInvestStockAPI;
  private fundamentusStockAPI: FundamentusStockAPI;

  constructor(private readonly page: Page) {
    const statusInvestStepsService = new StatusInvestStepsService(this.page);
    const statusInvestXPathService = new StatusInvestXPathService();
    this.statusInvestStockAPI = new StatusInvestStockAPI(
      this.page,
      statusInvestStepsService,
      statusInvestXPathService
    );

    const fundamentusStepsService = new FundamentusStepsService(this.page);
    const fundamentusXPathService = new FundamentusXPathService();
    this.fundamentusStockAPI = new FundamentusStockAPI(
      this.page,
      fundamentusStepsService,
      fundamentusXPathService
    );
  }

  async searchAndExtractData(code: string) {
    const statusInvest =
      await this.statusInvestStockAPI.search<StatusInvestStockDTO>(code);
    const fundamentus =
      await this.fundamentusStockAPI.search<FundamentusStockDTO>(code);

    return { ...fundamentus, ...statusInvest };
  }
}

export { StockFacade };
