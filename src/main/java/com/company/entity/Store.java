package com.company.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "store")
public class Store {
    @Id
    private int id;
    private String title;
    private String district;
    private int commission;
}
