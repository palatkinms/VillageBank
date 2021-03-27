package ru.bank.VillageBank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name="transaction")
@Data
@NoArgsConstructor
public class TransactionLog implements Comparable<TransactionLog>{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_donor_id")
    private Account accountDonor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_recipient_id")
    private Account accountRecipient;

    @Column(name="fund")
    private Integer fund;

    @Column(name="data")
    private Timestamp timestamp;

    public TransactionLog(Account accountDonor, Account accountRecipient, Integer fund, Timestamp timestamp) {
        this.accountDonor = accountDonor;
        this.accountRecipient = accountRecipient;
        this.fund = fund;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(TransactionLog o) {
        return o.getTimestamp().compareTo(o.getTimestamp());
    }
}
