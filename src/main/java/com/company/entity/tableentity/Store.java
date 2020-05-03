package com.company.entity.tableentity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String district;
    private int commission;

    public void copy(Store store) {
        this.title = store.getTitle();
        this.district = store.getDistrict();
        this.commission = store.getCommission();
    }
}
