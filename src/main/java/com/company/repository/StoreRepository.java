package com.company.repository;

import com.company.entity.tableentity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store,Integer> {

    @Query(value = "select title from store where store.district = 'Сормовский' or store.district = 'Советский'"
            ,nativeQuery = true)
    List<String> getStoreTittle();
}
