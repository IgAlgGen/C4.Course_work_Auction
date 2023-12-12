package com.example.c4_course_work_auction.Mapper;

import com.example.c4_course_work_auction.DTO.Bid;
import com.example.c4_course_work_auction.Entity.BidEntity;
import org.springframework.stereotype.Component;

@Component
public class BidMapper {

    public Bid toDTO(BidEntity bidEntity){
        Bid bidDTO = new Bid();
        bidDTO.setBidderName(bidEntity.getName());
        bidDTO.setBidDate(bidEntity.getDateTime());
        return bidDTO;
    }
}