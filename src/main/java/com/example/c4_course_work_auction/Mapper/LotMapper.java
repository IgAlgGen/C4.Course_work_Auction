package com.example.c4_course_work_auction.Mapper;

import com.example.c4_course_work_auction.DTO.*;
import com.example.c4_course_work_auction.Entity.LotEntity;
import org.springframework.stereotype.Component;

@Component
public class LotMapper {

    public LotEntity toEntity(CreateLot createLot) {
        LotEntity lotEntity = new LotEntity();
        lotEntity.setTitle(createLot.getTitle());
        lotEntity.setDescription(createLot.getDescription());
        lotEntity.setStartPrice(createLot.getStartPrice());
        lotEntity.setBidPrice(createLot.getBidPrice());
        lotEntity.setStatus(Status.CREATED);
        return lotEntity;
    }

    public Lot toDTO(LotEntity lotEntity) {
        Lot lotDTO = new Lot();
        lotDTO.setId(lotEntity.getId());
        lotDTO.setTitle(lotEntity.getTitle());
        lotDTO.setDescription(lotEntity.getDescription());
        lotDTO.setStatus(lotEntity.getStatus());
        lotDTO.setBidPrice(lotEntity.getBidPrice());
        lotDTO.setStartPrice(lotEntity.getStartPrice());
        return lotDTO;
    }

    public FullLot toDTO(FullLotView fullLotView) {
        FullLot fullLotDTO = new FullLot();
        fullLotDTO.setId(fullLotView.getId());
        fullLotDTO.setStatus(fullLotView.getStatus());
        fullLotDTO.setTitle(fullLotView.getTitle());
        fullLotDTO.setDescription(fullLotView.getDescription());
        fullLotDTO.setStartPrice(fullLotView.getStart_price());
        fullLotDTO.setBidPrice(fullLotView.getBid_price());
        fullLotDTO.setCurrentPrice(fullLotView.getCurrentPrice());
        fullLotDTO.setLastBid(new Bid(
                fullLotView.getName(),
                fullLotView.getDate_time()
        ));
        return fullLotDTO;
    }



}
