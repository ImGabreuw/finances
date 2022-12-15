import { Announcement } from "../../announcement-search-engine/domain/announcement";
import { parseLocalFormat } from "../../helpers/date-helper";
import { PuppeteerHelper } from "../../helpers/puppeteer-helper";
import { PuppeteerLauncher } from "../../puppeteer-launcher";
import { AssetNotFoundError } from "./errors/asset-not-found-error";

export async function getAssetPageUrlInStatusInvest(
  assetCode: string
): Promise<string> {
  const { browser, page } = await PuppeteerLauncher.launch();

  await page.goto("https://statusinvest.com.br");

  await PuppeteerHelper.click(
    page,
    `#main-nav-nav > div > div > div > ul > li:nth-child(2) > a > i`,
    "left"
  );

  await PuppeteerHelper.type(
    page,
    `#main-search > div.input-form > span.twitter-typeahead > input.Typeahead-input.input.tt-input`,
    assetCode
  );

  const assetNotFound = !(await PuppeteerHelper.isElementExists(
    page,
    `#main-search > div.Typeahead-menu > div > div`
  ));

  if (assetNotFound) {
    throw new AssetNotFoundError(assetCode);
  }

  await PuppeteerHelper.click(
    page,
    `#main-search > div.Typeahead-menu > div > div > a`,
    "left"
  );

  await browser.close();

  return page.url();
}

export class StatusInvestAnnouncementsGateway {
  constructor() {}

  async getLastAnnouncement(assetCode: string): Promise<Announcement> {
    const url = await getAssetPageUrlInStatusInvest(assetCode);

    const { browser, page } = await PuppeteerLauncher.launch();

    await page.goto(url);
    await PuppeteerHelper.scroll(page, `#document-section > div > h2`)

    const rawTitle = await PuppeteerHelper.extractTextFrom(
      page,
      `/html/body/main/div[10]/div/div[3]/div[1]/div[1]/div[1]/span[1]`
    );
    const title = rawTitle !== null ? rawTitle.trim() : "";

    const rawReleaseDate =
      (await PuppeteerHelper.extractTextFrom(
        page,
        `/html/body/main/div[10]/div/div[3]/div[1]/div[1]/div[2]`
      )) || "";
    const releaseDate = parseLocalFormat(rawReleaseDate);

    const rawDownloadUrl = await PuppeteerHelper.extractHrefFrom(
      page,
      `#document-section > div > div.documents.card > div.list > div:nth-child(1) > div:nth-child(3) > div > a`
    );
    const downloadUrl = rawDownloadUrl !== null ? rawDownloadUrl.trim() : "";

    await browser.close();

    return new Announcement(assetCode, title, releaseDate, downloadUrl);
  }
}
