package ru.bank.VillageBank.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="client")
@Data
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="lastname")
    private String lastname;

    @Column(name="address")
    private String address;

    @Column(name="photo")
    private String photo;

    @OneToMany(mappedBy = "client")
    private List<Account> accountList;

    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }


}
