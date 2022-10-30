import { Page } from "puppeteer";
import { API } from "../../domain/adapters/api.js";
import { CurrencyHelper } from "../../helpers/currency-helper.js";
import { NumberHelper } from "../../helpers/number-helper.js";
import { FundamentusStepsService } from "../service/fundamentus-steps-service.js";
import { FundamentusXPathService } from "../service/fundamentus-xpath-service.js";

class FundamentusStockAPI extends API {
  constructor(
    page: Page,
    fundamentusStepsService: FundamentusStepsService,
    fundamentusXPathService: FundamentusXPathService
  ) {
    super(
      page,
      fundamentusStepsService,
      fundamentusXPathService,
      "https://www.fundamentus.com.br/index.php",
      "https://www.fundamentus.com.br/detalhes.php?papel="
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

export { FundamentusStockAPI };
