import { Page } from "puppeteer";
import { FundamentusStockDTO } from "../gateway/dto/fundamentus-stock-dto";
import { StatusInvestStockDTO } from "../gateway/dto/status-invest-stock-dto";
import { FundamentusStockAPI } from "../gateway/fundamentus-stock-api";
import { StatusInvestStockAPI } from "../gateway/status-invest-stock-api";
import { FundamentusStepsService } from "../service/fundamentus-steps-service";
import { FundamentusXPathService } from "../service/fundamentus-xpath-service";
import { StatusInvestStepsService } from "../service/status-invest-steps-service";
import { StatusInvestXPathService } from "../service/status-invest-xpath-service";

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
