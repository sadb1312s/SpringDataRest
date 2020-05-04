package com.company.repository;

import com.company.entity.helpentity.InBuyerDistrictBuy;
import com.company.entity.jointables.BuyJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuyJoinRepository extends JpaRepository<BuyJoin, Integer> {
    List<BuyJoin> findAllBySumGreaterThanEqual (double sum);

}
