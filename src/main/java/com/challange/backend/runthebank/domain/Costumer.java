package com.challange.backend.runthebank.domain;

import com.challange.backend.runthebank.domain.enums.TipoPessoa;

public class Costumer {

    private String name;
    private String document;
    private String address;
    private String password;
    private TipoPessoa tipoPessoa;

    public Costumer(String name, String document, String address, String password, TipoPessoa tipoPessoa) {
        this.name = name;
        this.document = document;
        this.address = address;
        this.password = password;
        this.tipoPessoa = tipoPessoa;
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
}
