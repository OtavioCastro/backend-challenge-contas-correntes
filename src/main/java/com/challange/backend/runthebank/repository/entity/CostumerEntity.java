package com.challange.backend.runthebank.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COSTUMER")
public class CostumerEntity {

    @Id
    private String document;
    private String name;
    private String address;
    private String password;
}
