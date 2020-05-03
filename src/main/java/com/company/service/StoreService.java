package com.company.service;

import com.company.entity.tableentity.Store;
import com.company.repository.StoreRepository;
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
        Optional<Store> optional = storeRepository.findById(id);
        return optional;
    }

    public void updateFull(Store store){
        Store storeOld = storeRepository.getOne(store.getId());

        if (storeOld != null){
            System.out.println("!");
            storeOld.copy(store);
            storeRepository.save(storeOld);
        }
    }

    public void update(int id, LinkedHashMap<String, String> data){
        System.out.println(data);


        Store storeForUpdate = storeRepository.findById(id).orElse(null);

        //maybe return false(or http status) if exception was thrown
        if(storeForUpdate != null) {
            data.forEach((k, v) -> {
                try {
                    Field f = Store.class.getDeclaredField(k);

                    f.setAccessible(true);
                    String className = f.getType().getCanonicalName();

                    Class c = ClassUtils.getClass(className);

                    if (c.isPrimitive()){
                        c = ClassUtils.primitiveToWrapper(c);
                    }

                    Constructor<?> ctor = c.getConstructor(String.class);
                    f.set(storeForUpdate,ctor.newInstance(v));

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

            storeRepository.save(storeForUpdate);
        }
    }


    public List<String> getTitle(){
        return storeRepository.getStoreTittle();
    }
}
