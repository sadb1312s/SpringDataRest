package com.company.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "buyer")
public class Buyer {
    @Id
    private int id;
    private String lastname;
    private String district;
    private int discount;
}
