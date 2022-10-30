import { Page } from "puppeteer";
import { API } from "../../domain/adapters/api.js";
import { CurrencyHelper } from "../../helpers/currency-helper.js";
import { NumberHelper } from "../../helpers/number-helper.js";
import { StatusInvestStepsService } from "../service/status-invest-steps-service.js";
import { StatusInvestXPathService } from "../service/status-invest-xpath-service.js";

class StatusInvestStockAPI extends API {
  constructor(
    page: Page,
    statusInvestStepsService: StatusInvestStepsService,
    statusInvestXPathService: StatusInvestXPathService
  ) {
    super(
      page,
      statusInvestStepsService,
      statusInvestXPathService,
      "https://statusinvest.com.br/",
      "https://statusinvest.com.br/acoes/"
    );
  }

  normalize(elementName: string, content: string | null): string | number {
    if (content === null) {
      return "-/-";
    }

    content = CurrencyHelper.removeBrazilianCurrencySymbol(content);
    content = NumberHelper.removePercentSymbol(content);
    content = NumberHelper.removeNumberFormat(content);

    if (NumberHelper.isNotNumber(content)) {
      return content.trim();
    }

    return Number(Number(content).toFixed(3));
  }
}

export { StatusInvestStockAPI };
