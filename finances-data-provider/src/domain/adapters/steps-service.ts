import {Page} from "puppeteer";
import {PuppeteerHelper} from "../../helpers/puppeteer-helper";
import {SectionSteps} from "../types/step-type";

const SEARCH_STEPS_SECTION_NAME = "search";

interface RegisterSearchSteps {
  registerSearchSteps(searchText: string): void;
}

abstract class StepsService {
  private readonly STEPS = new Map<string, SectionSteps>();

  constructor(private readonly page: Page) {}

  hasSteps(sectionName: string): boolean {
    return this.STEPS.has(sectionName);
  }

  register(sectionSteps: SectionSteps): void {
    const sectionName = sectionSteps.sectionName;

    if (this.hasSteps(sectionName)) {
      throw new Error(
        `uma seção de etapas já foi registrada com o nome "${sectionName}"`
      );
    }

    this.STEPS.set(sectionName, sectionSteps);
  }

  async execute(sectionName: string): Promise<void> {
    if (!this.hasSteps(sectionName)) return;

    const section = this.STEPS.get(sectionName);

    if (section?.steps === undefined) {
      throw new Error("seção de etapas inválida");
    }

    for (const { action, text, selector } of section.steps) {
      if (action === "type") {
        if (!text) {
          throw new Error("texto inválido");
        }

        await PuppeteerHelper.type(this.page, selector, text);

        continue;
      }

      await PuppeteerHelper.click(this.page, selector, action);
    }
  }
}

export { StepsService, RegisterSearchSteps, SEARCH_STEPS_SECTION_NAME };
