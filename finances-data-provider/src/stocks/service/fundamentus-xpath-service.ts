import { XPathService } from "../../domain/adapters/xpath-service.js";

class FundamentusXPathService extends XPathService {
  constructor() {
    super();
  }

  registerAll(): void {
    this.register({
      sectionName: "Indicatores de preço",
      xPaths: [
        {
          elementName: "Preço atual",
          xPath: `/html/body/div[1]/div[2]/table[1]/tbody/tr[1]/td[4]/span`,
        },
        {
          elementName: "Preço mínimo (12 meses)",
          xPath: `/html/body/div[1]/div[2]/table[1]/tbody/tr[3]/td[4]/span`,
        },
        {
          elementName: "Preço máximo (12 meses)",
          xPath: `/html/body/div[1]/div[2]/table[1]/tbody/tr[4]/td[4]/span`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de valorização",
      xPaths: [
        {
          elementName: "Valorização (mês atual)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[3]/td[2]/span/font`,
        },
        {
          elementName: "Valorização (30 dias)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[4]/td[2]/span/font`,
        },
        {
          elementName: "Valorização (12 meses)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[5]/td[2]/span/font`,
        },
        {
          elementName: "Valorização (2022)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[6]/td[2]/span/font`,
        },
        {
          elementName: "Valorização (2021)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[7]/td[2]/span/font`,
        },
        {
          elementName: "Valorização (2020)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[8]/td[2]/span/font`,
        },
        {
          elementName: "Valorização (2019)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[9]/td[2]/span/font`,
        },
        {
          elementName: "Valorização (2018)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[10]/td[2]/span/font`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de negociação",
      xPaths: [
        {
          elementName: "Tipo ação",
          xPath: `/html/body/div[1]/div[2]/table[1]/tbody/tr[2]/td[2]/span`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de valuation",
      xPaths: [
        {
          elementName: "Dividend yield (12 meses)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[9]/td[4]/span`,
        },
        {
          elementName: "P/L",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[2]/td[4]/span`,
        },
        {
          elementName: "P/VP",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[3]/td[4]/span`,
        },
        {
          elementName: "EV/EBITDA",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[10]/td[4]/span`,
        },
        {
          elementName: "EV/EBIT",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[11]/td[4]/span`,
        },
        {
          elementName: "P/EBIT",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[4]/td[4]/span`,
        },
        {
          elementName: "VPA",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[3]/td[6]/span`,
        },
        {
          elementName: "P/Ativo",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[6]/td[4]/span`,
        },
        {
          elementName: "LPA",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[2]/td[6]/span`,
        },
        {
          elementName: "P/SR",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[5]/td[4]/span`,
        },
        {
          elementName: "P/Capital de giro",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[7]/td[4]/span`,
        },
        {
          elementName: "P/Ativo circulante liquido",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[8]/td[4]/span`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de endividamento",
      xPaths: [
        {
          elementName: "Dívida bruta/PL",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[11]/td[6]/span`,
        },
        {
          elementName: "EBIT/Ativo",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[7]/td[6]/span`,
        },
        {
          elementName: "Liquidez corrente",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[10]/td[6]/span`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de eficiência",
      xPaths: [
        {
          elementName: "Margem bruta",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[4]/td[6]/span`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de rentabilidade",
      xPaths: [
        {
          elementName: "ROE",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[9]/td[6]/span`,
        },
        {
          elementName: "ROIC",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[8]/td[6]/span`,
        },
        {
          elementName: "Giro do ativo",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[12]/td[6]/span`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de crescimento",
      xPaths: [
        {
          elementName: "CAGR receitas (5 anos)",
          xPath: `/html/body/div[1]/div[2]/table[3]/tbody/tr[12]/td[4]/span`,
        },
      ],
    });
  }
}

export { FundamentusXPathService };
