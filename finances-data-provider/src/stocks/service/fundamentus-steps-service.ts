import {Page} from "puppeteer";
import {RegisterSearchSteps, StepsService,} from "../../domain/adapters/steps-service";

class FundamentusStepsService
  extends StepsService
  implements RegisterSearchSteps
{
  constructor(page: Page) {
    super(page);
    this.registerAll();
  }

  registerSearchSteps(searchText: string): void {
    this.register({
      sectionName: "fundamentus search steps",
      steps: [
        {
          elementName: "barra de pesquisa",
          action: "left_click",
          selector: `#completar`,
        },
        {
          elementName: "barra de pesquisa",
          action: "type",
          text: searchText,
          selector: `#completar`,
        },
        {
          elementName: "botão de buscar",
          action: "left_click",
          selector: `body > div.center.dinheiro > div.topo > form > fieldset > input.botao`,
        },
      ],
    });
  }

  registerAll() {}
}

export { FundamentusStepsService };
