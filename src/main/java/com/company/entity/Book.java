package com.company.entity;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.websocket.server.PathParam;

@Data
@Entity
@Table (name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ApiParam(value = "name that need to be updated", required = true)
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
