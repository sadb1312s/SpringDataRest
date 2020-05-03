package com.company.entity.tableentity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table (name = "buy")
public class Buy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    //@OneToMany(targetEntity = Store.class, mappedBy = "id", fetch = FetchType.LAZY)
    private int idstore;
    //@OneToMany(targetEntity = Buyer.class, mappedBy = "id", fetch = FetchType.LAZY)
    private int idbuyer;
    //@OneToMany(targetEntity = Book.class, mappedBy = "id", fetch = FetchType.LAZY)
    private int idbook;
    private int count;
    private double sum;

    public void copy(Buy buy) {
        this.date = buy.date;
        this.idstore = buy.idstore;
        this.idbuyer = buy.idbuyer;
        this.idbook = buy.idbook;
        this.count = buy.count;
        this.sum = buy.sum;
    }
}
