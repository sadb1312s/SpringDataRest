package com.company.entity.tableentity;


import com.company.service.updatetable.Updatable;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table (name = "buyer")
public class Buyer implements Updatable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String lastname;
    private String district;
    private int discount;

    public void copy(Buyer other){
        this.lastname = other.getLastname();
        this.district = other.getDistrict();
        this.discount = other.getDiscount();
    }
}
