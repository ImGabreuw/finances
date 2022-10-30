import { DTO } from "../../../domain/adapters/dto";

class StatusInvestStockDTO implements DTO {
  /*
  Indicadores de preço
  */
  readonly "Preço atual": number;
  readonly "Preço mínimo (12 meses)": number;
  readonly "Preço mínimo (mês atual)": number;
  readonly "Preço máximo (12 meses)": number;
  readonly "Preço máximo (mês atual)": number;
  /*
  Indicadores de valorização
  */
  readonly "Valorização (12 meses)": number;
  readonly "Valorização (mês atual)": number;
  /*
  Indicadores de negociação
  */
  readonly "Tipo ação": string;
  readonly "Tag along": number;
  readonly "Liquidez diária média (30 dias)": number;
  readonly "Participação no IBOV": number;
  /*
  Indicadores de valuation
  */
  readonly "Dividend yield (12 meses)": number;
  readonly "Dividendos (12 meses)": number;
  readonly "P/L": number;
  readonly "PEG Ratio": number;
  readonly "P/VP": number;
  readonly "EV/EBITDA": number;
  readonly "EV/EBIT": number;
  readonly "P/EBITDA": number;
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
  readonly "Dívida líquida/PL": number;
  readonly "Dívida líquida/EBITDA": number;
  readonly "Dívida líquida/EBIT": number;
  readonly "PL/Ativos": number;
  readonly "Passivos/Ativos": number;
  readonly "Liquidez corrente": number;
  /*
  Indicadores de eficiência
  */
  readonly "Margem bruta": number;
  readonly "Margem EBITDA": number;
  readonly "Margem EBIT": number;
  readonly "Margem líquida": number;
  /*
  Indicadores de rentabilidade
  */
  readonly "ROE": number;
  readonly "ROA": number;
  readonly "ROIC": number;
  readonly "Giro do ativo": number;
  /*
  Indicadores de crescimento
  */
  readonly "CAGR receitas (5 anos)": number;
  readonly "CAGR lucros (5 anos)": number;
  /*
  Payout
  */
  readonly "Payout médio": number;
  readonly "Payout atual": number;
  readonly "Payout mínimo": number;
  readonly "Payout mínimo (ano)": number;
  readonly "Payout máximo": number;
  readonly "Payout máximo (ano)": number;
  /*
  Informações da empresa
  */
  readonly "Nome da empresa": string;
  readonly "Patrimônio líquido": number;
  readonly "Ativos": number;
  readonly "Ativo circulante": number;
  readonly "Dívida bruta": number;
  readonly "Caixa": number;
  readonly "Dívida líquida": number;
  readonly "Valor de mercado": number;
  readonly "Valor da empresa": number;
  readonly "Número total de ações": number;
  readonly "Free float": number;
  readonly "Segmento de listagem": string;
  readonly "Setor de atuação": string;
  readonly "Subsetor de atuação": string;
  readonly "Segmento de atuação": string;

  constructor() {}
}

export { StatusInvestStockDTO };
