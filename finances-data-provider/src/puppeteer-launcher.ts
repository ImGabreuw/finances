import { Browser, launch, Page } from "puppeteer";

class PuppeteerLauncher {
  private static readonly INSTANCE: PuppeteerLauncher;

  private constructor(readonly browser: Browser, readonly page: Page) {}

  static async launch(): Promise<PuppeteerLauncher> {
    if (!this.INSTANCE) {
      const browser = await launch({ headless: false });
      const page = await browser.newPage();

      await page.setViewport({ width: 1200, height: 1000 });

      return new PuppeteerLauncher(browser, page);
    }

    return this.INSTANCE;
  }
}

export { PuppeteerLauncher };
