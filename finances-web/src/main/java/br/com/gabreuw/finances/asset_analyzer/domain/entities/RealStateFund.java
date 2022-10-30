package br.com.gabreuw.finances.asset_analyzer.domain.entities;

import java.time.LocalDate;

public class RealStateFund {

    /*
    INDICADORES DE PREÇO
     */

    public Double currentPrice;
    public Double minPriceLast1Year;
    public Double minPriceCurrentMonth;
    public Double maxPriceLast1Year;
    public Double maxPriceCurrentMonth;

    /*
    INDICADORES DE DIVIDENDOS
     */

    public Double dividendYieldLast1Year;
    public Double dividendLast1Year;

    /*
    INDICADORES DE VARIAÇÃO
     */

    public Double dayVariation;
    public Double variationLast1Year;
    public Double variationCurrentMonth;

    /*
    INDICADORES FINANCEIROS
     */

    public Double equityValue;
    public Double patrimony;
    public Double PVP;
    public Double marketValue;
    public Double equityCashValue;
    public Double cashValue;

    /*
    INDICADORES DE CRESCIMENTO
     */

    public Double dividendYieldCAGRLast3Years;
    public Double dividendYieldCAGRLast5Years;
    public Double valueCAGRLast3Years;
    public Double valueCAGRLast5Years;

    /*
    INFORMAÇÕES DO FUNDO
     */

    public String ticketName;
    public LocalDate fundStartDate;
    public String ANBIMAType;
    public String ANBIMASegment;
    public String segment;
    public String typeOfManagement;
    public String targetAudience;
    public String administrationTax;
    public Integer numberOfShareholder;
    public Integer numberOfShare;
    public Double averageMonthlyDividendLast2Years;
    public Double averageDailyLiquidity;
    public Double participationInIFIX;

}
