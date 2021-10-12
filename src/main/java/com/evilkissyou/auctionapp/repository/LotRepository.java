package com.evilkissyou.auctionapp.repository;

import com.evilkissyou.auctionapp.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Integer> {
    List<Lot> findByCategoryIdOrderByEndDateAsc(int id);
    @Query(value = "select * from lots l where l.name like %:keyword%", nativeQuery = true)
    List<Lot> findByKeyword(@Param("keyword") String keyword);
}
