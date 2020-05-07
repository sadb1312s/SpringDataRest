package com.company.entity.tableentity;



import com.company.service.updatetable.Updatable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "book")
public class Book implements Updatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private String warehouse;
    private int count;

    public void copy(Book other){
        this.title = other.getTitle();
        this.price = other.getPrice();
        this.warehouse = other.getWarehouse();
        this.count = other.getCount();
    }
}
