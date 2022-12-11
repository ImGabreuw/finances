import { Page } from "puppeteer";
import { API } from "../../domain/adapters/api";
import { NumberHelper } from "../../helpers/number-helper";
import { ReclameAquiStepsService } from "../service/reclame-aqui-steps-service";
import { ReclameAquiXPathService } from "../service/reclame-aqui-xpath-service";

class ReclameAquiAPI extends API {
  constructor(
    page: Page,
    reclameAquiStepsService: ReclameAquiStepsService,
    reclameAquiXPathService: ReclameAquiXPathService
  ) {
    super(
      page,
      reclameAquiStepsService,
      reclameAquiXPathService,
      "https://www.reclameaqui.com.br/",
      "https://www.reclameaqui.com.br/empresa/",
    );
  }

  normalize(elementName: string, content: string | null): string | number {
    if (content === null) {
      return "-/-";
    }

    content = NumberHelper.removePercentSymbol(content);

    if (NumberHelper.isNotNumber(content)) {
      return content.trim();
    }

    return Number(Number(content).toFixed(3));
  }
}

export { ReclameAquiAPI };
