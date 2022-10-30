import {XPathService} from "../../domain/adapters/xpath-service.js";

class RealStateFundXPathService extends XPathService {
  constructor() {
    super();
  }

  registerAll(): void {
    this.register({
      sectionName: "Indicadores de preço",
      xPaths: [
        {
          elementName: "Preço atual",
          xPath: `//*[@id="main-2"]/div[2]/div[1]/div[1]/div/div[1]/strong`,
        },
        {
          elementName: "Preço mínimo (12 meses)",
          xPath: `//*[@id="main-2"]/div[2]/div[1]/div[2]/div/div[1]/strong`,
        },
        {
          elementName: "Preço mínimo (mês atual)",
          xPath: `//*[@id="main-2"]/div[2]/div[1]/div[2]/div/div[2]/div/span[2]`,
        },
        {
          elementName: "Preço máximo (12 meses)",
          xPath: `//*[@id="main-2"]/div[2]/div[1]/div[3]/div/div[1]/strong`,
        },
        {
          elementName: "Preço máximo (mês atual)",
          xPath: `//*[@id="main-2"]/div[2]/div[1]/div[3]/div/div[2]/div/span[2]`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de valorização",
      xPaths: [
        {
          elementName: "Valorização (12 meses)",
          xPath: `//*[@id="main-2"]/div[2]/div[1]/div[5]/div/div[1]/strong`,
        },
        {
          elementName: "Valorização (mês atual)",
          xPath: `//*[@id="main-2"]/div[2]/div[1]/div[5]/div/div[2]/div/span[2]/b`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de mercado",
      xPaths: [
        {
          elementName: "Valor de mercado",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[2]/div/div[2]/span[2]`,
        },
        {
          elementName: "Liquidez diária média (30 dias)",
          xPath: `//*[@id="main-2"]/div[2]/div[6]/div/div/div[3]/div/div/div/strong`,
        },
        {
          elementName: "Participação no IFIX",
          xPath: `//*[@id="main-2"]/div[2]/div[6]/div/div/div[4]/div/a/div/div/strong`,
        },
        {
          elementName: "Número de cotistas",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[6]/div/div[1]/strong`,
        },
        {
          elementName: "Número de cotas",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[6]/div/div[2]/span[2]`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de valuation",
      xPaths: [
        {
          elementName: "Dividend yield (12 meses)",
          xPath: `//*[@id="main-2"]/div[2]/div[1]/div[4]/div/div[1]/strong`,
        },
        {
          elementName: "Rendimento (12 meses)",
          xPath: `/html/body/main/div[2]/div[1]/div[4]/div/div[2]/div/span[2]`,
        },
        {
          elementName: "Rendimento mensal médio (2 anos)",
          xPath: `//*[@id="main-2"]/div[2]/div[6]/div/div/div[1]/div/div/strong`,
        },
        {
          elementName: "P/VP",
          xPath: `/html/body/main/div[2]/div[5]/div/div[2]/div/div[1]/strong`,
        },
        {
          elementName: "Valor patrimonial",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[1]/div/div[1]/strong`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de crescimento",
      xPaths: [
        {
          elementName: "Dividend yield CAGR (3 anos)",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[4]/div/div[1]/strong`,
        },
        {
          elementName: "Dividend yield CAGR (5 anos)",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[4]/div/div[2]/span[2]`,
        },
        {
          elementName: "Valor CAGR (3 anos)",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[5]/div/div[1]/strong`,
        },
        {
          elementName: "Valor CAGR (5 anos)",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[5]/div/div[2]/span[2]`,
        },
      ],
    });

    this.register({
      sectionName: "Informações do fundo",
      xPaths: [
        {
          elementName: "Nome do fundo",
          xPath: `//*[@id="fund-section"]/div/div/div[2]/div/div[2]/div/div/div/strong`,
        },
        {
          elementName: "Administrador",
          xPath: `/html/body/main/div[3]/div/div/div[3]/div/div[2]/div[1]/div/strong`,
        },
        {
          elementName: "Data de fundação",
          xPath: `//*[@id="fund-section"]/div/div/div[2]/div/div[3]/div/div/strong`,
        },
        {
          elementName: "Prazo de duração",
          xPath: `//*[@id="fund-section"]/div/div/div[2]/div/div[4]/div/div/div/strong`,
        },
        {
          elementName: "Tipo ANBIMA",
          xPath: `//*[@id="fund-section"]/div/div/div[2]/div/div[5]/div/div/div/strong`,
        },
        {
          elementName: "Segmento ANBIMA",
          xPath: `//*[@id="fund-section"]/div/div/div[2]/div/div[6]/div/div/strong`,
        },
        {
          elementName: "Segmento",
          xPath: `//*[@id="fund-section"]/div/div/div[4]/div/div[1]/div/div/div/a/strong`,
        },
        {
          elementName: "Tipo da gestão",
          xPath: `//*[@id="fund-section"]/div/div/div[4]/div/div[2]/div/div/div/strong`,
        },
        {
          elementName: "Público alvo",
          xPath: `//*[@id="fund-section"]/div/div/div[4]/div/div[3]/div/div/div/strong`,
        },
        {
          elementName: "Patrimônio",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[1]/div/div[2]/span[2]`,
        },
        {
          elementName: "Caixa (%)",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[3]/div/div[1]/strong`,
        },
        {
          elementName: "Caixa (R$)",
          xPath: `//*[@id="main-2"]/div[2]/div[5]/div/div[3]/div/div[2]/span[2]`,
        },
        {
          elementName: "Taxa de administração",
          xPath: `//*[@id="main-2"]/div[2]/div[6]/div/div/div[2]/div/div/div/strong`,
        },
      ],
    });
  }
}

export {RealStateFundXPathService};
