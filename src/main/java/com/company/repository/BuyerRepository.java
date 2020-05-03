package com.company.repository;

import com.company.entity.tableentity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyer,Integer> {
    @Query(value = "select district from buyer group by district", nativeQuery = true)
    List<String> getDistricts();

    @Query(value = "select lastname,discount from buyer b where b.district = 'Нижегородский'",nativeQuery = true)
    List<String> getNameDiscount();
}
