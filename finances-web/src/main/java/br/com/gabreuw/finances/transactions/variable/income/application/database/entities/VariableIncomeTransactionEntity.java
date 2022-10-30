package br.com.gabreuw.finances.transactions.variable.income.application.database.entities;

import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.variable.income.domain.entities.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VariableIncomeTransactionEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String assetCode;

    @Enumerated(STRING)
    @Column(nullable = false)
    private AssetType assetType;

    @Column(nullable = false)
    private LocalDate operationDate;

    @Enumerated(STRING)
    @Column(nullable = false)
    private OperationType operationType;

    @Column(nullable = false)
    private Double operationAverageCost;

    @Column(nullable = false)
    private Integer numberOfShares;

    @Column(nullable = false)
    private Double totalInvested;

}
