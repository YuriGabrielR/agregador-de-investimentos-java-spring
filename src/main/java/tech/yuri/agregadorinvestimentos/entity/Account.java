package tech.yuri.agregadorinvestimentos.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    private String description;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn()
    private BillingAddress billingAddress;

    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountsStocks;

    public Account() {
    }

    public Account(UUID accountId, String description, User user, BillingAddress billingAddress, List<AccountStock> accountsStocks)
    {
        this.accountId = accountId;
        this.description = description;
        this.user = user;
        this.billingAddress = billingAddress;
        this.accountsStocks = accountsStocks;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<AccountStock> getAccountsStocks() {
        return accountsStocks;
    }

    public void setAccountsStocks(List<AccountStock> accountsStocks) {
        this.accountsStocks = accountsStocks;
    }
}
