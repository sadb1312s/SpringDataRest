package com.company.repository;

import com.company.entity.helpentity.jointables.BuyJoin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyJoinRepository extends JpaRepository<BuyJoin, Integer> {
    List<BuyJoin> findAllBySumGreaterThanEqual (double sum);

}
