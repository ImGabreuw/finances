import { Page } from "puppeteer";
import { NumberHelper } from "../../shared/helpers/number-helper";
import { PuppeteerHelper } from "../../shared/helpers/puppeteer-helper";
import { ReflectionHelper } from "../../shared/helpers/reflection-helper";
import { DTO } from "./dto";
import { SEARCH_STEPS_SECTION_NAME, StepsService } from "./steps-service";
import { XPathService } from "./xpath-service";

abstract class API {
  protected constructor(
    protected readonly page: Page,
    protected readonly stepsService: StepsService,
    protected readonly xpathService: XPathService,
    protected readonly baseUrl: string,
    protected readonly endpointUrl: string
  ) {}

  async search<T extends DTO>(searchText: string): Promise<T> {
    return await this.tryOrRetry(searchText);
  }

  async extract<T extends DTO>(): Promise<T> {
    const dto = Object.create({}) as T;

    const sections = this.xpathService.getAll();

    for (const { sectionName, xPaths } of sections) {
      await this.stepsService.execute(sectionName);

      for (const { elementName, xPath } of xPaths) {
        const content = await PuppeteerHelper.extractTextFrom(this.page, xPath);
        const normalizedContent = this.normalize(elementName, content);

        if (NumberHelper.isNotNumber(normalizedContent)) {
          eval(`dto["${elementName}"] = "${normalizedContent}"`);
          continue;
        }

        eval(`dto["${elementName}"] = ${normalizedContent}`);
      }
    }

    return dto;
  }

  async tryOrRetry<T extends DTO>(
    searchText: string,
    count: number = 0
  ): Promise<T> {
    if (count > 1) return {} as T;

    if (count === 0) {
      const response = await this.page.goto(
        `${this.endpointUrl}${searchText}`
      );

      if (!response?.ok()) {
        return await this.tryOrRetry(searchText, ++count);
      }
    } else {
      if (ReflectionHelper.isImplementsRegisterSearchSteps(this.stepsService)) {
        this.stepsService.registerSearchSteps(searchText);

        await this.page.goto(this.baseUrl);

        await this.stepsService.execute(SEARCH_STEPS_SECTION_NAME);
      } else {
        console.warn(
          `É recomendado implementar a busca por nome ("RegisterSearchSteps")`
        );
      }
    }

    try {
      return await this.extract();
    } catch (error) {
      console.log(error);
      return await this.tryOrRetry(searchText, ++count);
    }
  }

  abstract normalize(elementName: string, content: string | null): any;
}

export { API };
