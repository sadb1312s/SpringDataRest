package com.company.repository;

import com.company.entity.helpentity.InBuyerDistrictBuy;
import com.company.entity.tableentity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyRepository extends JpaRepository<Buy, Integer> {
    @Query(value = "select to_char(date, 'month') as m from buy group by m",nativeQuery = true)
    List<String> getMonth();

    @Query(value = "select bu.lastname,bu.district,b.date from buy b " +
                        "join buyer bu on b.idbuyer = bu.id " +
                        "join store s on b.idstore = s.id " +
                        "where  s.district = bu.district " +
                        "and date_part('month', date) > 2 " +
                        "order by b.date"
            ,nativeQuery = true)
    List<InBuyerDistrictBuy> getBuyInBuyerDistrict();
}
