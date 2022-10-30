import { DTO } from "../../../domain/adapters/dto";

class RealStateFundDTO implements DTO {
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
  Indicadores de mercado
  */
  readonly "Valor de mercado": number;
  readonly "Liquidez diária média (30 dias)": number;
  readonly "Participação no IFIX": number;
  readonly "Número de cotistas": number;
  readonly "Número de cotas": number;
  /*
  Indicadores de valuation
  */
  readonly "Dividend yield (12 meses)": number;
  readonly "Rendimento (12 meses)": number;
  readonly "Rendimento mensal médio (2 anos)": number;
  readonly "P/VP": number;
  readonly "Valor patrimonial": number;
  /*
  Indicadores de crescimento
  */
  readonly "Dividend yield CAGR (3 anos)": number;
  readonly "Dividend yield CAGR (5 anos)": number;
  readonly "Valor CAGR (3 anos)": number;
  readonly "Valor CAGR (5 anos)": number;
  /*
  Informações do fundo
  */
  readonly "Nome do fundo": string;
  readonly "Administrador": string;
  readonly "Data de fundação": string;
  readonly "Prazo de duração": string;
  readonly "Tipo ANBIMA": string;
  readonly "Segmento ANBIMA": string;
  readonly "Segmento": string;
  readonly "Tipo da gestão": string;
  readonly "Público alvo": string;
  readonly "Patrimônio": number;
  readonly "Caixa (%)": number;
  readonly "Caixa (R$)": number;
  readonly "Taxa de administração": string;
}

export { RealStateFundDTO };
