package com.company.entity.tableentity;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table (name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden=true)
    private int id;
    private String title;
    private double price;
    private String warehouse;
    private int count;

    /*@ApiModelProperty(hidden=true)
    @OneToMany(mappedBy = "book")
    Set<Buy> buys;*/

    public void copy(Book other){
        this.title = other.getTitle();
        this.price = other.getPrice();
        this.warehouse = other.getWarehouse();
        this.count = other.getCount();
    }
}
