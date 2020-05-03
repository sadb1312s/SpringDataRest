package com.company.repository;

import com.company.entity.tableentity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, Integer> {
    @Query(value = "select to_char(date, 'month') as m from buy group by m",nativeQuery = true)
    List<String> getMonth();

    /*@Query(value = "select bu.lastname, s.title from buy b\n" +
            "\tjoin buyer bu on b.idbuyer = bu.id\n" +
            "\tjoin store s on b.idstore = s.id", nativeQuery = true)*/

}
