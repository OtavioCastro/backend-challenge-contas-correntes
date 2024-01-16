package com.challenge.backend.runthebank.repository.entity;

import com.challenge.backend.runthebank.domain.enums.TipoPessoa;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "COSTUMER")
public class CostumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID costumerId;
    @Column(unique = true)
    private String document;
    private String name;
    private String address;
    private String password;
    private TipoPessoa tipoPessoa;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AccountEntity> accounts = new ArrayList<>();

    public CostumerEntity() {
    }

    public CostumerEntity(String document, String name, String address, String password, List<AccountEntity> accounts) {
        this.document = document;
        this.name = name;
        this.address = address;
        this.password = password;
        this.accounts = accounts;
    }

    public UUID getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(UUID costumerId) {
        this.costumerId = costumerId;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoPessoa getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(TipoPessoa tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public List<AccountEntity> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountEntity> accounts) {
        this.accounts = accounts;
    }
}
