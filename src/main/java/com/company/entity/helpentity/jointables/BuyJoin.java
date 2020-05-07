package com.company.entity.helpentity.jointables;

import com.company.entity.tableentity.Book;
import com.company.entity.tableentity.Buyer;
import com.company.entity.tableentity.Store;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Entity
@Table (name = "buy")
public class BuyJoin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden=true)
    private int id;
    private Date date;


    @ManyToOne
    @JoinColumn(name = "idstore")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "idbuyer")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "idbook")
    private Book book;

    private int count;
    private double sum;
}

