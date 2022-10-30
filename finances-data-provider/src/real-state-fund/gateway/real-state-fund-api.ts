import { Page } from "puppeteer";
import { API } from "../../domain/adapters/api.js";
import { CurrencyHelper } from "../../helpers/currency-helper.js";
import { NumberHelper } from "../../helpers/number-helper.js";
import { RealStateFundStepsService } from "../services/real-state-fund-steps-service.js";
import { RealStateFundXPathService } from "../services/real-state-fund-xpath-service.js";

class RealStateFundAPI extends API {
  constructor(
    page: Page,
    realStateFundStepsService: RealStateFundStepsService,
    realStateFundXPathService: RealStateFundXPathService
  ) {
    super(
      page,
      realStateFundStepsService,
      realStateFundXPathService,
      "https://statusinvest.com.br/",
      "https://statusinvest.com.br/fundos-imobiliarios/"
    );
  }

  normalize(elementName: string, content: string | null): string | number {
    if (content === null) {
      return "-/-";
    }

    if (elementName === "Taxa de administração") {
      return content.trim().toLowerCase();
    }

    content = CurrencyHelper.removeBrazilianCurrencySymbol(content);
    content = NumberHelper.removePercentSymbol(content);
    content = NumberHelper.removeNumberFormat(content);

    if (NumberHelper.isNotNumber(content)) {
      content = content.trim();
      return content.charAt(0).toUpperCase() + content.slice(1).toLowerCase();
    }

    return Number(Number(content).toFixed(3));
  }
}

export { RealStateFundAPI };
