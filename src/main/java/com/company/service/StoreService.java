package com.company.service;

import com.company.entity.tableentity.Store;
import com.company.repository.StoreRepository;
import com.company.service.updatetable.Updater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class StoreService {
    @Autowired
    private StoreRepository storeRepository;

    public void delete(int id){
        storeRepository.deleteById(id);
    }

    public void save(Store store){
        storeRepository.save(store);
    }

    public List<Store> findAll(){
        return storeRepository.findAll();
    }

    public Optional<Store> findById(int id){
        return storeRepository.findById(id);
    }

    public void updateFull(Store store){
        Store storeOld = storeRepository.getOne(store.getId());

        if (storeOld != null){
            System.out.println("!");
            storeOld.copy(store);
            storeRepository.save(storeOld);
        }
    }

    public void update(int id, Map<String, String> data){
        System.out.println(data);


        Store storeForUpdate = storeRepository.findById(id).orElse(null);

        //maybe return false(or http status) if exception was thrown
        if(storeForUpdate != null) {
            Updater.update(storeForUpdate,data, Store.class);

            storeRepository.save(storeForUpdate);
        }
    }


    public List<String> getTitle(){
        return storeRepository.getStoreTittle();
    }

    public List<Store> getStoreConditionDiscount() {
        return storeRepository.getConditionByDiscount();
    }
}
