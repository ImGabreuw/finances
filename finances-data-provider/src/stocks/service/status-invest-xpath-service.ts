import { XPathService } from "../../domain/adapters/xpath-service.js";

class StatusInvestXPathService extends XPathService {
  constructor() {
    super();
  }

  registerAll(): void {
    this.register({
      sectionName: "Indicatores de preço",
      xPaths: [
        {
          elementName: "Preço atual",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[1]/div/div[1]/strong`,
        },
        {
          elementName: "Preço mínimo (12 meses)",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[2]/div/div[1]/strong`,
        },
        {
          elementName: "Preço mínimo (mês atual)",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[2]/div/div[2]/div/span[2]`,
        },
        {
          elementName: "Preço máximo (12 meses)",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[3]/div/div[1]/strong`,
        },
        {
          elementName: "Preço máximo (mês atual)",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[3]/div/div[2]/div/span[2]`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de valorização",
      xPaths: [
        {
          elementName: "Valorização (12 meses)",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[5]/div/div[1]/strong`,
        },
        {
          elementName: "Valorização (mês atual)",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[5]/div/div[2]/div/span[2]/b`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de negociação",
      xPaths: [
        {
          elementName: "Tipo ação",
          xPath: `/html/body/main/div[2]/div/div[5]/div/div/div[1]/div/div/strong`,
        },
        {
          elementName: "Tag along",
          xPath: `/html/body/main/div[2]/div/div[5]/div/div/div[2]/div/div/div/strong`,
        },
        {
          elementName: "Liquidez diária média (30 dias)",
          xPath: `/html/body/main/div[2]/div/div[5]/div/div/div[3]/div/div/div/strong`,
        },
        {
          elementName: "Participação no IBOV",
          xPath: `/html/body/main/div[2]/div/div[5]/div/div/div[4]/div/a/div/div/strong`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de valuation",
      xPaths: [
        {
          elementName: "Dividend yield (12 meses)",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[4]/div/div[1]/strong`,
        },
        {
          elementName: "Dividendos (12 meses)",
          xPath: `/html/body/main/div[2]/div/div[1]/div/div[4]/div/div[2]/div/span[2]`,
        },
        {
          elementName: "P/L",
          xPath: `//*[@id="indicators-section"]/div[2]/div/div[1]/div/div[2]/div/div/strong`,
        },
        {
          elementName: "PEG Ratio",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[3]/div/div/strong`,
        },
        {
          elementName: "P/VP",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[4]/div/div/strong`,
        },
        {
          elementName: "EV/EBITDA",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[5]/div/div/strong`,
        },
        {
          elementName: "EV/EBIT",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[6]/div/div/strong`,
        },
        {
          elementName: "P/EBITDA",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[7]/div/div/strong`,
        },
        {
          elementName: "P/EBIT",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[8]/div/div/strong`,
        },
        {
          elementName: "VPA",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[9]/div/div/strong`,
        },
        {
          elementName: "P/Ativo",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[10]/div/div/strong`,
        },
        {
          elementName: "LPA",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[11]/div/div/strong`,
        },
        {
          elementName: "P/SR",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[12]/div/div/strong`,
        },
        {
          elementName: "P/Capital de giro",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[13]/div/div/strong`,
        },
        {
          elementName: "P/Ativo circulante liquido",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[1]/div/div[14]/div/div/strong`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de endividamento",
      xPaths: [
        {
          elementName: "Dívida líquida/PL",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[2]/div/div[1]/div/div/strong`,
        },
        {
          elementName: "Dívida líquida/EBITDA",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[2]/div/div[2]/div/div/strong`,
        },
        {
          elementName: "Dívida líquida/EBIT",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[2]/div/div[3]/div/div/strong`,
        },
        {
          elementName: "PL/Ativos",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[2]/div/div[4]/div/div/strong`,
        },
        {
          elementName: "Passivos/Ativos",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[2]/div/div[5]/div/div/strong`,
        },
        {
          elementName: "Liquidez corrente",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[2]/div/div[6]/div/div/strong`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de eficiência",
      xPaths: [
        {
          elementName: "Margem bruta",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[3]/div/div[1]/div/div/strong`,
        },
        {
          elementName: "Margem EBITDA",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[3]/div/div[2]/div/div/strong`,
        },
        {
          elementName: "Margem EBIT",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[3]/div/div[3]/div/div/strong`,
        },
        {
          elementName: "Margem líquida",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[3]/div/div[4]/div/div/strong`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de rentabilidade",
      xPaths: [
        {
          elementName: "ROE",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[4]/div/div[1]/div/div/strong`,
        },
        {
          elementName: "ROA",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[4]/div/div[2]/div/div/strong`,
        },
        {
          elementName: "ROIC",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[4]/div/div[3]/div/div/strong`,
        },
        {
          elementName: "Giro do ativo",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[4]/div/div[4]/div/div/strong`,
        },
      ],
    });

    this.register({
      sectionName: "Indicadores de crescimento",
      xPaths: [
        {
          elementName: "CAGR receitas (5 anos)",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[5]/div/div[1]/div/div/strong`,
        },
        {
          elementName: "CAGR lucros (5 anos)",
          xPath: `/html/body/main/div[2]/div/div[7]/div[2]/div/div[5]/div/div[2]/div/div/strong`,
        },
      ],
    });

    this.register({
      sectionName: "Payout",
      xPaths: [
        {
          elementName: "Payout médio",
          xPath: `/html/body/main/div[4]/div/div/div/div[1]/div[1]/div[1]/strong`,
        },
        {
          elementName: "Payout atual",
          xPath: `/html/body/main/div[4]/div/div/div/div[1]/div[1]/div[2]/strong/span`,
        },
        {
          elementName: "Payout mínimo",
          xPath: `/html/body/main/div[4]/div/div/div/div[1]/div[1]/div[3]/strong/span`,
        },
        {
          elementName: "Payout mínimo (ano)",
          xPath: `/html/body/main/div[4]/div/div/div/div[1]/div[1]/div[3]/strong/small/span`,
        },
        {
          elementName: "Payout máximo",
          xPath: `/html/body/main/div[4]/div/div/div/div[1]/div[1]/div[4]/strong/span`,
        },
        {
          elementName: "Payout máximo (ano)",
          xPath: `/html/body/main/div[4]/div/div/div/div[1]/div[1]/div[4]/strong/small/span`,
        },
      ],
    });

    this.register({
      sectionName: "Informações da empresa",
      xPaths: [
        {
          elementName: "Nome da empresa",
          xPath: `/html/body/main/div[5]/div[1]/div/div[1]/div[2]/h4/span`,
        },
        {
          elementName: "Patrimônio líquido",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[1]/div/div/strong`,
        },
        {
          elementName: "Ativos",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[2]/div/div/strong`,
        },
        {
          elementName: "Ativo circulante",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[3]/div/div/div/strong`,
        },
        {
          elementName: "Dívida bruta",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[4]/div/div/strong`,
        },
        {
          elementName: "Caixa",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[5]/div/div/strong`,
        },
        {
          elementName: "Dívida líquida",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[6]/div/div/strong`,
        },
        {
          elementName: "Valor de mercado",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[7]/div/div/strong`,
        },
        {
          elementName: "Valor da empresa",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[8]/div/div/strong`,
        },
        {
          elementName: "Número total de ações",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[9]/div/div/strong`,
        },
        {
          elementName: "Free float",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[11]/div/div/strong`,
        },
        {
          elementName: "Segmento de listagem",
          xPath: `/html/body/main/div[5]/div[1]/div/div[2]/div[10]/div/div/strong`,
        },
        {
          elementName: "Setor de atuação",
          xPath: `/html/body/main/div[5]/div[1]/div/div[3]/div/div[1]/div/div/div/a/strong`,
        },
        {
          elementName: "Subsetor de atuação",
          xPath: `/html/body/main/div[5]/div[1]/div/div[3]/div/div[2]/div/div/div/a/strong`,
        },
        {
          elementName: "Segmento de atuação",
          xPath: `/html/body/main/div[5]/div[1]/div/div[3]/div/div[3]/div/div/div/a/strong`,
        },
      ],
    });
  }
}

export { StatusInvestXPathService };
