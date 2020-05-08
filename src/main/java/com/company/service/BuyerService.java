package com.company.service;

import com.company.controller.exception.NotFoundException;
import com.company.entity.helpentity.NameDiscount;
import com.company.entity.tableentity.Buyer;
import com.company.repository.BuyerRepository;
import com.company.service.updatetable.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyerService {
    @Autowired
    private BuyerRepository buyerRepository;

    public void delete(int id){
        buyerRepository.deleteById(id);
    }

    public void save(Buyer book){
        buyerRepository.save(book);
    }

    public List<Buyer> findAll(){
        return buyerRepository.findAll();
    }

    public Buyer findById(int id){
        Buyer buyer = buyerRepository.findById(id).orElse(null);

        if(buyer == null){
            throw new NotFoundException();
        }else {
            return buyer;
        }
    }

    public void updateFull(Buyer book){
        Buyer buyerOld = findById(book.getId());

        if (buyerOld != null){
            buyerOld.copy(book);
            buyerRepository.save(buyerOld);
        }
    }

    public void update(int id, Map<String, String> data){

        Buyer buyerForUpdate = findById(id);

        if(buyerForUpdate != null) {
            Updater.update(buyerForUpdate,data, Buyer.class);
            buyerRepository.save(buyerForUpdate);
        }
    }

    public List<String> getDistricts(){
        return buyerRepository.getDistricts();
    }

    public List<NameDiscount> getNameDiscount(){
        return buyerRepository.getNameDiscount();
    }
}
