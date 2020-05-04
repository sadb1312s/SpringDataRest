package com.company.repository;

import com.company.entity.jointables.BuyJoin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyJoinRepository extends JpaRepository<BuyJoin, Integer> {
}
