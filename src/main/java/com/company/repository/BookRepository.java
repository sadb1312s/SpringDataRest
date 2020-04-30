package com.company.repository;

import com.company.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface BookRepository extends JpaRepository<Book,Integer> {

    //this don't work as i suppose
    /*@Transactional
    @Modifying
    @Query(value = "update book set price = :value where id = 1 ",nativeQuery = true)
    int getCount(@Param("value") String value);*/

}

