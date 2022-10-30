import { Page } from "puppeteer";
import { ReclameAquiDTO } from "../gateway/dto/reclame-aqui-dto.js";
import { ReclameAquiAPI } from "../gateway/reclame-aqui-api.js";
import { ReclameAquiStepsService } from "../service/reclame-aqui-steps-service.js";
import { ReclameAquiXPathService } from "../service/reclame-aqui-xpath-service.js";

class ReclameAquiFacade {
  constructor(private readonly page: Page) {}

  async searchAndExtractData(enterpriseName: string) {
    const reclameAquiStepsService = new ReclameAquiStepsService(this.page);
    const reclameAquiXPathService = new ReclameAquiXPathService();
    const reclameAquiAPI = new ReclameAquiAPI(
      this.page,
      reclameAquiStepsService,
      reclameAquiXPathService
    );

    return await reclameAquiAPI.search<ReclameAquiDTO>(enterpriseName);
  }
}

export { ReclameAquiFacade };
