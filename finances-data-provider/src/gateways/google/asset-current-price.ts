import { SearchInGoogle } from "./search-in-google";
import { PuppeteerHelper } from "../../shared/helpers/puppeteer-helper";
import { NumberHelper } from "../../shared/helpers/number-helper";

export class AssetCurrentPrice {
 async getAssetCurrentPrice(assetCode: string): Promise<number> {
  const { browser, page } = await new SearchInGoogle().search(assetCode);

  const rawCurrentPrice = await PuppeteerHelper.extractTextFrom(
   page,
   `/html/body/div[7]/div/div[11]/div[2]/div[2]/div[2]/div/div/div/div/div/div/div[2]/div[2]/div[2]/div/div/div/div/div/div/div/div[3]/g-card-section/div/g-card-section/div[2]/div[1]/span[1]/span/span[1]`,
  );

  const currentPrice = Number(
   NumberHelper.removeNumberFormat(rawCurrentPrice || ""),
  );

  await browser.close();

  return currentPrice;
 }
}
