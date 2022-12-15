import express, { Request, Response } from "express";
import { PuppeteerLauncher } from "./puppeteer-launcher";
import { RealStateFundFacade } from "./real-state-fund/facade/real-state-fund-facade";
import { routes } from "./routes";
import { StockFacade } from "./stocks/facade/stock-facade";

export const app = express();

const port = 3000;

app.listen(port, () => {
  console.log(`listening on port ${port}`);
});

app.use(express.json());
app.use(routes)

app.get(
  "/real-state-fund/:assetCode",
  async (request: Request, response: Response) => {
    const { assetCode } = request.params;
    const { browser, page } = await PuppeteerLauncher.launch();

    try {
      const result = await new RealStateFundFacade(page).searchAndExtractData(
        assetCode
      );

      return response.json(result);
    } catch ({ errorMessage }) {
      return response.status(400).json({
        error: errorMessage,
      });
    } finally {
      await browser.close();
    }
  }
);

app.get("/stock/:assetCode", async (request: Request, response: Response) => {
  const { assetCode } = request.params;
  const { browser, page } = await PuppeteerLauncher.launch();

  try {
    const result = await new StockFacade(page).searchAndExtractData(assetCode);

    return response.json(result);
  } catch ({ errorMessage }) {
    return response.status(400).json({
      error: errorMessage,
    });
  } finally {
    await browser.close();
  }
});
