package com.company.entity.tableentity;


import com.company.service.updatetable.Updatable;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table (name = "buy")
public class Buy implements Updatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;

    private int idstore;
    private int idbuyer;
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
