import { Browser, Page } from "puppeteer";
import { PuppeteerLauncher } from "../../puppeteer-launcher";
import { PuppeteerHelper } from "../../shared/helpers/puppeteer-helper";

const constants = {
 baseUrl: "https://www.google.com.br/",
 searchUrl: "https://www.google.com/search?q=",
} as const;

export class SearchInGoogle {
 async search(textToSearch: string): Promise<{ browser: Browser; page: Page }> {
  const { browser, page } = await PuppeteerLauncher.launch();

  await page.goto(constants.baseUrl);

  await PuppeteerHelper.type(
   page,
   `body > div.L3eUgb > div.o3j99.ikrT4e.om7nvf > form > div:nth-child(1) > div.A8SBwf > div.RNNXgb > div > div.a4bIc > input`,
   textToSearch,
  );
  await page.keyboard.press("Enter");

  return { browser, page };
 }
}
