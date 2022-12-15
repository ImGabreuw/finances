import { Page } from "puppeteer";
import { API } from "../../domain/adapters/api";
import { CurrencyHelper } from "../../shared/helpers/currency-helper";
import { NumberHelper } from "../../shared/helpers/number-helper";
import { StatusInvestStepsService } from "../service/status-invest-steps-service";
import { StatusInvestXPathService } from "../service/status-invest-xpath-service";

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
