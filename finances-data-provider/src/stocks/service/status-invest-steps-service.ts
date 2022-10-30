import {Page} from "puppeteer";
import {RegisterSearchSteps, StepsService,} from "../../domain/adapters/steps-service.js";

class StatusInvestStepsService
  extends StepsService
  implements RegisterSearchSteps
{
  constructor(page: Page) {
    super(page);
    this.registerAll();
  }

  registerSearchSteps(searchText: string): void {
    this.register({
      sectionName: "status invest search steps",
      steps: [
        {
          elementName: "lupa",
          action: "left_click",
          selector: `#main-nav-nav > div > div > div > ul > li:nth-child(2) > a > i`,
        },
        {
          elementName: "barra de pesquisa",
          action: "type",
          text: searchText,
          selector: `#main-search > div.input-form > span.twitter-typeahead > input.Typeahead-input.input.tt-input`,
        },
        {
          elementName: "selecionar opção",
          action: "left_click",
          selector: `#main-search > div.Typeahead-menu > div > div > a`,
        },
      ],
    });
  }

  registerAll(): void {
    this.register({
      sectionName: "Payout",
      steps: [
        {
          elementName: "max",
          action: "left_click",
          selector: `#payout-section > div > div > div.d-md-flex.justify-between.align-items-center.mb-2.mb-lg-4 > div.mt-3.mt-md-0 > div > div > ul > li:nth-child(3) > a`,
        },
      ],
    });
  }
}

export { StatusInvestStepsService };
