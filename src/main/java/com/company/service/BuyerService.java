package com.company.service;

import com.company.entity.tableentity.Buyer;
import com.company.repository.BuyerRepository;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
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
        Optional<Buyer> optional = buyerRepository.findById(id);
        return optional;
    }

    public void updateFull(Buyer book){
        Buyer buyerOld = buyerRepository.getOne(book.getId());

        if (buyerOld != null){
            buyerOld.copy(book);
            buyerRepository.save(buyerOld);
        }
    }

    public void update(int id, LinkedHashMap<String, String> data){
        System.out.println(data);


        Buyer buyerForUpdate = buyerRepository.findById(id).orElse(null);

        //maybe return false(or http status) if exception was thrown
        if(buyerForUpdate != null) {
            data.forEach((k, v) -> {
                try {
                    Field f = Buyer.class.getDeclaredField(k);

                    f.setAccessible(true);
                    String className = f.getType().getCanonicalName();

                    Class c = ClassUtils.getClass(className);

                    if (c.isPrimitive()){
                        c = ClassUtils.primitiveToWrapper(c);
                    }

                    Constructor<?> ctor = c.getConstructor(String.class);
                    f.set(buyerForUpdate,ctor.newInstance(v));

                } catch (NoSuchFieldException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });

            buyerRepository.save(buyerForUpdate);
        }
    }

    public List<String> getDistricts(){
        return buyerRepository.getDistricts();
    }

    public List<String> getNameDiscount(){
        return buyerRepository.getNameDiscount();
    }
}
