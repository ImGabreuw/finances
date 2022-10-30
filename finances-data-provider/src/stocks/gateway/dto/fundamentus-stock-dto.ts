import { DTO } from "../../../domain/adapters/dto";

class FundamentusStockDTO implements DTO {
  /*
  Indicadores de preço
  */
  readonly "Preço atual": number;
  readonly "Preço mínimo (12 meses)": number;
  readonly "Preço máximo (12 meses)": number;
  /*
  Indicadores de valorização
  */
  readonly "Valorização (mês atual)": number;
  readonly "Valorização (30 dias)": number;
  readonly "Valorização (12 meses)": number;
  readonly "Valorização (2022)": number;
  readonly "Valorização (2021)": number;
  readonly "Valorização (2020)": number;
  readonly "Valorização (2019)": number;
  readonly "Valorização (2018)": number;
  /*
  Indicadores de negociação
  */
  readonly "Tipo ação": string;
  /*
  Indicadores de valuation
  */
  readonly "Dividend yield (12 meses)": number;
  readonly "P/L": number;
  readonly "P/VP": number;
  readonly "EV/EBITDA": number;
  readonly "EV/EBIT": number;
  readonly "P/EBIT": number;
  readonly "VPA": number;
  readonly "P/Ativo": number;
  readonly "LPA": number;
  readonly "P/SR": number;
  readonly "P/Capital de giro": number;
  readonly "P/Ativo circulante liquido": number;
  /*
  Indicadores de endividamento
  */
  readonly "Dívida bruta/PL": number;
  readonly "EBIT/Ativo": number;
  readonly "Liquidez corrente": number;
  /*
  Indicadores de eficiência
  */
  readonly "Margem bruta": number;
  /*
  Indicadores de rentabilidade
  */
  readonly "ROE": number;
  readonly "ROIC": number;
  readonly "Giro do ativo": number;
  /*
  Indicadores de crescimento
  */
  readonly "CAGR receitas (5 anos)": number;

  constructor() {}
}

export { FundamentusStockDTO };
