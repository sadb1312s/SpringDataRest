package com.company.entity.tableentity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table (name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden=true)
    private int id;
    private String title;
    private String district;
    private int commission;

    /*@ApiModelProperty(hidden=true)
    @OneToMany(mappedBy = "store")
    Set<Buy> buys;*/

    public void copy(Store store) {
        this.title = store.getTitle();
        this.district = store.getDistrict();
        this.commission = store.getCommission();
    }
}
