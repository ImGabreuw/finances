import {Page} from "puppeteer";
import {RegisterSearchSteps, SEARCH_STEPS_SECTION_NAME, StepsService} from "../../domain/adapters/steps-service.js";

class ReclameAquiStepsService
  extends StepsService
  implements RegisterSearchSteps
{
  constructor(page: Page) {
    super(page);
    this.registerAll();
  }

  registerSearchSteps(searchText: string): void {
    this.register({
      sectionName: SEARCH_STEPS_SECTION_NAME,
      steps: [
        {
          elementName: "barra de pesquisa",
          action: "left_click",
          selector: `#header-search-bar`,
        },
        {
          elementName: "barra de pesquisa",
          action: "type",
          text: searchText,
          selector: `#header-search-bar`,
        },
        {
          elementName: "selecionar opção",
          action: "left_click",
          selector: `#react-tabs-1 > div > div > div > div > div.slick-slide.slick-active.slick-current > div > a > div.sc-cTJkRt.lmGFyU`,
        },
      ],
    });
  }

  private registerAll(): void {
    this.register({
      sectionName: "reputação",
      steps: [
        {
          elementName: "geral",
          action: "left_click",
          selector: `#reputation-tab-5`,
        },
      ],
    });
  }
}

export { ReclameAquiStepsService };
