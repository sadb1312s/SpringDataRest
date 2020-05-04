package com.company.entity.tableentity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table (name = "buyer")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden=true)
    private int id;
    private String lastname;
    private String district;
    private int discount;

    /*@ApiModelProperty(hidden=true)
    @OneToMany(mappedBy = "buyer")
    Set<Buy> buys;*/

    public void copy(Buyer other){
        this.lastname = other.getLastname();
        this.district = other.getDistrict();
        this.discount = other.getDiscount();
    }
}
