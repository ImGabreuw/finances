import { Page } from "puppeteer";
import { ReclameAquiFacade } from "../../reclame-aqui/facade/reclame-aqui-facade";
import { RealStateFundDTO } from "../gateway/dto/real-state-fund-dto";
import { RealStateFundAPI } from "../gateway/real-state-fund-api";
import { RealStateFundStepsService } from "../services/real-state-fund-steps-service";
import { RealStateFundXPathService } from "../services/real-state-fund-xpath-service";

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
