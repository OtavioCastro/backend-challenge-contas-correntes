package com.challenge.backend.runthebank.domain;

import com.challenge.backend.runthebank.domain.enums.TipoPessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Costumer {

    private UUID costumerId;
    private String name;
    private String document;
    private String address;
    private String password;
    private TipoPessoa tipoPessoa;
    private List<Account> accounts = new ArrayList<>();

    public Costumer() {
    }

    public Costumer(String name, String document, String address, String password, TipoPessoa tipoPessoa) {
        this.name = name;
        this.document = document;
        this.address = address;
        this.password = password;
        this.tipoPessoa = tipoPessoa;
    }

    public Costumer(UUID costumerId, String name, String document, String address, String password, TipoPessoa tipoPessoa, List<Account> accounts) {
        this.costumerId = costumerId;
        this.name = name;
        this.document = document;
        this.address = address;
        this.password = password;
        this.tipoPessoa = tipoPessoa;
        this.accounts = accounts;
    }

    public UUID getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(UUID costumerId) {
        this.costumerId = costumerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
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

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
