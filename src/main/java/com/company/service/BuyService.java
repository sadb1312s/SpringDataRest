package com.company.service;

import com.company.entity.helpentity.InBuyerDistrictBuy;
import com.company.entity.jointables.BuyJoin;
import com.company.entity.tableentity.Buy;
import com.company.repository.BuyJoinRepository;
import com.company.repository.BuyRepository;
import org.apache.commons.lang3.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


//need triggers for sum field ?
@Service
public class BuyService {
    @Autowired
    BuyRepository buyRepository;
    @Autowired
    BuyJoinRepository buyJoinRepository;

    public void delete(int id){
        buyRepository.deleteById(id);
    }

    public void save(Buy buy){
        buyRepository.save(buy);
    }

    public List<Buy> findAll(){
        return buyRepository.findAll();
    }

    public Optional<Buy> findById(int id){
        Optional<Buy> optional = buyRepository.findById(id);
        return optional;
    }

    public void updateFull(Buy buy){
        Buy buyOld = buyRepository.getOne(buy.getId());

        if (buyOld != null){
            buyOld.copy(buy);
            buyRepository.save(buyOld);
        }
    }

    public void update(int id, LinkedHashMap<String, String> data){
        System.out.println(data);


        Buy buyerForUpdate = buyRepository.findById(id).orElse(null);

        //maybe return false(or http status) if exception was thrown
        if(buyerForUpdate != null) {
            data.forEach((k, v) -> {
                try {
                    Field f = Buy.class.getDeclaredField(k);

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

            buyRepository.save(buyerForUpdate);
        }
    }

    public List<String> getMonth(){
        return buyRepository.getMonth();
    }


    public List<String> getJoinTable() {
        List<BuyJoin> buys = buyJoinRepository.findAll();

        List<String> result = new ArrayList<>();
        buys.forEach((buyJoin -> result.add("lastname = '"+buyJoin.getBuyer().getLastname()+"', " +
                "store title = '"+buyJoin.getStore().getTitle()+"'")
        ));

        return result;
    }


    public List<String> getBuyerBookInfo() {
        List<BuyJoin> buys = buyJoinRepository.findAll();

        List<String> result = new ArrayList<>();
        buys.forEach((buyJoin -> result.add("date = '"+buyJoin.getDate()+"', " +
                "last name = '"+buyJoin.getBuyer().getLastname()+"' " +
                "discount = '"+buyJoin.getBuyer().getDiscount()+"' " +
                "book Title = '"+buyJoin.getBook().getTitle()+"' " +
                "count = '"+buyJoin.getCount()+"'")
        ));

        return result;

    }

    public List<String> getBigBuy(double condition){
        List<BuyJoin> buys = buyJoinRepository.findAllBySumGreaterThanEqual(condition);

        List<String> result = new ArrayList<>();
        buys.forEach((buyJoin -> result.add("buy id = '"+buyJoin.getId()+"', " +
                "last name = '"+buyJoin.getBuyer().getLastname()+"' " +
                "discount = '"+buyJoin.getDate()+"' ")
        ));

        return result;


    }

    public List<InBuyerDistrictBuy> getBuyInBuyerDistrict(){
        return buyRepository.getBuyInBuyerDistrict();
    }

}
