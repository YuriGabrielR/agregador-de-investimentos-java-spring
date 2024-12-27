package tech.yuri.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="billing_address")
public class BillingAddress {
    @Id
    @Column(name="account_id")
    private UUID id;

    private String street;

    private Number number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    @MapsId
    private Account account;

    public BillingAddress() {
    }

    public BillingAddress(UUID id, String street, Number number, Account account) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
