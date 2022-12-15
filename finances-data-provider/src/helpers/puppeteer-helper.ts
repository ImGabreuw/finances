import { MouseButton, Page, TimeoutError } from "puppeteer";

class PuppeteerHelper {
  private constructor() {}

  static async extractTextFrom(
    page: Page,
    xpath: string
  ): Promise<string | null> {
    try {
      await page.waitForXPath(xpath, { timeout: 7_000 });

      const elementHandle = await page.$x(xpath);

      return await page.evaluate((tag) => tag.textContent, elementHandle[0]);
    } catch (error) {
      if (error instanceof TimeoutError) {
        console.log(`element (${xpath}) not found. Skipping...`);
        return null;
      }

      throw error;
    }
  }

  static async extractHrefFrom(
    page: Page,
    selector: string
  ): Promise<string | null> {
    try {
      await page.waitForSelector(selector, { timeout: 7_000 });

      return await page.$eval(selector, (anchor) =>
        anchor.getAttribute("href")
      );
    } catch (error) {
      if (error instanceof TimeoutError) {
        console.log(`element (${selector}) not found. Skipping...`);
        return null;
      }

      throw error;
    }
  }

  static async click(
    page: Page,
    selector: string,
    buttonAction: string
  ): Promise<void> {
    try {
      buttonAction = PuppeteerHelper.getClickTypeFromText(buttonAction);

      await page.waitForSelector(selector, { timeout: 7_000 });
      await page.click(selector, { button: buttonAction as MouseButton });
      await page.waitForTimeout(1_000);
    } catch (error) {
      if (error instanceof TimeoutError) {
        console.log(`element (${selector}) not found. Skipping...`);
        return;
      }

      throw error;
    }
  }

  static async type(page: Page, selector: string, text: string): Promise<void> {
    try {
      await page.waitForSelector(selector, { timeout: 7_000 });
      await page.type(selector, text.trim().toLowerCase());
      await page.waitForTimeout(1_000);
    } catch (error) {
      if (error instanceof TimeoutError) {
        console.log(`element (${selector}) not found. Skipping...`);
        return;
      }

      throw error;
    }
  }

  static getClickTypeFromText(text: string): string {
    const clickType = text.split("_")[0];

    if (clickType !== "left" && clickType !== "right") return "left";

    return clickType;
  }

  static async isElementExists(page: Page, selector: string): Promise<boolean> {
    try {
      await page.waitForSelector(selector, { timeout: 3_000 });
      return true;
    } catch {
      return false;
    }
  }

  static async scroll(page: Page, selector: string): Promise<void> {
    await page.$eval(selector, (element) => {
      element.scrollIntoView({
        behavior: "smooth",
        block: "end",
        inline: "end",
      });
    });
  }
}

export { PuppeteerHelper };
