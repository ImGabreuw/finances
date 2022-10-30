import { Page } from "puppeteer";
import { ReclameAquiFacade } from "../../reclame-aqui/facade/reclame-aqui-facade.js";
import { RealStateFundDTO } from "../gateway/dto/real-state-fund-dto.js";
import { RealStateFundAPI } from "../gateway/real-state-fund-api.js";
import { RealStateFundStepsService } from "../services/real-state-fund-steps-service.js";
import { RealStateFundXPathService } from "../services/real-state-fund-xpath-service.js";

class RealStateFundFacade {
  constructor(private readonly page: Page) {}

  async searchAndExtractData(code: string) {
    const realStateFundStepsService = new RealStateFundStepsService(this.page);
    const realStateFundXPathService = new RealStateFundXPathService();
    const realStateFundAPI = new RealStateFundAPI(
      this.page,
      realStateFundStepsService,
      realStateFundXPathService
    );

    const realStateFund = await realStateFundAPI.search<RealStateFundDTO>(code);

    const reclameAquiFacade = new ReclameAquiFacade(this.page);
    const reclameAqui = reclameAquiFacade.searchAndExtractData(
      realStateFund.Administrador
    );

    return { ...realStateFund, ...reclameAqui };
  }
}

export { RealStateFundFacade };
