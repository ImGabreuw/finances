package br.com.gabreuw.finances.transactions.fixed.income.application.database.entities;

import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.AssetType;
import br.com.gabreuw.finances.transactions.fixed.income.domain.entities.enums.Indexer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FixedIncomeTransactionEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Enumerated(STRING)
    @Column(nullable = false)
    private AssetType assetType;

    @Column(nullable = false)
    private String issuingAgency;

    @Lob
    private String description;

    @Column(nullable = false)
    private Double profitabilityInPercent;

    @Enumerated(STRING)
    @Column(nullable = false)
    private Indexer indexer;

    @Column(nullable = false)
    private String liquidity;

    @Column(nullable = false)
    private LocalDate expiresIn;

    @Column(nullable = false)
    private Double totalInvested;

    @Column(nullable = false)
    private Double units;

    @Column(nullable = false)
    private LocalDate operationDate;

}
