package com.company.repository;

import com.company.entity.tableentity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Integer> {

    @Query(value = "select title from store where store.district = 'Сормовский' or store.district = 'Советский'"
            ,nativeQuery = true)
    List<String> getStoreTittle();

    @Query(value = "select s.id, s.title, s.district, s.commission from buy b " +
            "join buyer bu on b.idbuyer = bu.id " +
            "join store s on b.idstore = s.id " +
            "where bu.discount > 10 and bu.discount < 15 " +
            "and s.district not like 'Автозаводский' " +
            "group by s.id", nativeQuery = true)
    List<Store> getConditionByDiscount();
}
