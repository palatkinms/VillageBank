package ru.bank.VillageBank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;

import javax.persistence.*;
import java.util.Comparator;

@Entity
@Table(name="account")
@Data
@NoArgsConstructor
public class Account implements Comparable<Account>{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true, nullable = false)
    private int number;

    @Column(name = "fund")
    private int fund;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public Account(int number, int fund, Client client) {
        this.number = number;
        this.fund = fund;
        this.client = client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number=" + number +
                ", fund=" + fund +
                '}';
    }

    @Override
    public int compareTo(Account o) {
        return number-o.getNumber();
    }
}
