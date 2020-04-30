package com.company.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table (name = "buy")
public class Buy {
    @Id
    private int id;
    private Date date;
    private int idstore;
    private int idbuyer;
    private int idbook;
    private int count;
    private double sum;
}
