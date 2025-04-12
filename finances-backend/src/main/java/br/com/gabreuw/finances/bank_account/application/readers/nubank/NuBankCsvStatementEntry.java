package br.com.gabreuw.finances.bank_account.application.readers.nubank;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * Exemplo:
 * <pre>
 *     Data,Valor,Identificador,Descrição
 *     01/03/2025,100.00,0acaccd4-7fdf-4a34-b462-9ed0c0e44d4c,Compra no débito - Mercado Livre
 * </pre>
 */
@Data
public class NuBankCsvStatementEntry {

    @CsvBindByName(column = "Data")
    private String date;

    /**
     * Armazena o valor absoluto da transação
     */
    @CsvBindByName(column = "Valor")
    private String value;

    @CsvBindByName(column = "Identificador")
    private String identifier;

    @CsvBindByName(column = "Descrição")
    private String description;

}
