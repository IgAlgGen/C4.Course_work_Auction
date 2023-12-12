package com.example.c4_course_work_auction.Repository;

import com.example.c4_course_work_auction.DTO.CSVView;
import com.example.c4_course_work_auction.DTO.FullLotView;
import com.example.c4_course_work_auction.DTO.Status;
import com.example.c4_course_work_auction.Entity.LotEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LotRepository extends CrudRepository<LotEntity, Integer> {
    //ObservationFilter getFullLot(int id);

    //    @Query("SELECT new com.example.c4_coursework_try.DTO.FullLot(" +
//            "l.id, " +
//            "l.status, " +
//            "l.title, " +
//            "l.description, " +
//            "l.startPrice, " +
//            "l.bidPrice," +
//            "12345," +  //todo Пока не понял как сюда засунуть подзапрос
//            "b.name, " +
//            "b.dateTime" +
//
//            ") FROM LotEntity l INNER JOIN BidEntity b ON b.lotEntity = l WHERE l.id = :lotId order by b.dateTime DESC LIMIT 1")
//    Optional<FullLot> getFullLot(@Param("lotId") int lotId);

    @Query(nativeQuery = true,
            value = "SELECT l.id,\n" +
                    "       l.title,\n" +
                    "       l.description,\n" +
                    "       l.start_price,\n" +
                    "       l.bid_price,\n" +
                    "       l.status,\n" +
                    "       (SELECT count(c.name) FROM public.bids c WHERE c.lot_id = :lotId) * l.bid_price + l.start_price as currentPrice,\n" +
                    "       b.name,\n" +
                    "       b.date_time\n" +
                    "FROM public.lots l\n" +
                    "         INNER JOIN public.bids b on l.id = b.lot_id\n" +
                    "WHERE b.lot_id = :lotId\n" +
                    "ORDER BY b.date_time DESC\n" +
                    "LIMIT 1")
    Optional<FullLotView> getFullLot(@Param("lotId") int id);

    Page<LotEntity> findAllByStatus(Status status, Pageable lotsPage);

    @Query(nativeQuery = true,
            value = "Select l.id,\n" +
                    "       l.title,\n" +
                    "       l.status,\n" +
                    "       b.name,\n" +
                    "       (SELECT count(b.name)\n" +
                    "        FROM public.bids b\n" +
                    "        WHERE b.lot_id = l.id) * l.bid_price + l.start_price\n" +
                    "           as currentPrice\n" +
                    "FROM lots l\n" +
                    "         left join bids b\n" +
                    "                   on l.id = b.lot_id\n" +
                    "                       and b.name = (select b.name\n" +
                    "                                     from bids b\n" +
                    "                                     where l.id = b.lot_id\n" +
                    "                                     group by b.name\n" +
                    "                                     ORDER BY max(b.date_time) desc\n" +
                    "                                     limit 1)\n" +
                    "GROUP BY l.id, b.name")
    List<CSVView> getAllLotsToCSV();
}
