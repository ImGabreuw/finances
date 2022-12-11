import { Page } from "puppeteer";
import { RegisterSearchSteps, SEARCH_STEPS_SECTION_NAME, StepsService } from "../../domain/adapters/steps-service";

class RealStateFundStepsService extends StepsService implements RegisterSearchSteps {
  constructor(page: Page) {
    super(page);
    this.registerAll();
  }

  registerSearchSteps(searchText: string): void {
    this.register({
      sectionName: SEARCH_STEPS_SECTION_NAME,
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

  registerAll(): void {}
}

export { RealStateFundStepsService };
