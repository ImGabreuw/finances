import { StatusInvestAnnouncementsGateway, StatusInvestSearchAssetGateway } from "../../../src/gateways/status_invest/status-invest-gateway";

test.skip("should get AGRO3 page url in Status Invest", async () => {
  const assetCode = "AGRO3";
  const statusInvestGateway = new StatusInvestSearchAssetGateway();

  const underTest = await statusInvestGateway.getAssetPageUrlInStatusInvest(assetCode);

  expect(underTest).toBe("https://statusinvest.com.br/acoes/agro3");
}, 30_000);

test("should get announcement info of AGRO3 from Status Invest", async () => {
  const assetCode = "AGRO3";
  const announcementGateway = new StatusInvestAnnouncementsGateway();

  const underTest = await announcementGateway.getLastAnnouncement(assetCode);

  console.log(underTest);

  expect(underTest).not.toBeNull();
}, 30_000);
