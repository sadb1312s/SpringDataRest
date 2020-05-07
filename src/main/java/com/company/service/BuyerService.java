package com.company.service;

import com.company.entity.helpentity.NameDiscount;
import com.company.entity.tableentity.Buyer;
import com.company.repository.BuyerRepository;
import com.company.service.updatetable.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<Buyer> findById(int id){
        return buyerRepository.findById(id);
    }

    public void updateFull(Buyer book){
        Buyer buyerOld = buyerRepository.getOne(book.getId());

        if (buyerOld != null){
            buyerOld.copy(book);
            buyerRepository.save(buyerOld);
        }
    }

    public void update(int id, Map<String, String> data){
        System.out.println(data);


        Buyer buyerForUpdate = buyerRepository.findById(id).orElse(null);


        //maybe return false(or http status) if exception was thrown
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
