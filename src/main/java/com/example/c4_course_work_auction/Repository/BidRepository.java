package com.example.c4_course_work_auction.Repository;

import com.example.c4_course_work_auction.DTO.Bid;
import com.example.c4_course_work_auction.Entity.BidEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BidRepository extends CrudRepository<BidEntity, Integer> {
    Optional<BidEntity> findFirstByLotEntity_IdOrderByDateTimeAsc(int lotId);
    @Query(value = "SELECT new com.example.c4_course_work_auction.DTO.Bid (b.name, b.dateTime) FROM BidEntity b WHERE b.name = (SELECT c.name FROM BidEntity c where c.lotEntity.id = :lotId group by c.name order by count(c.name) desc limit 1) order by b.dateTime desc limit 1")
    Optional<Bid> searchTheMostFrequentBidder(@Param("lotId") int lotId);
}
