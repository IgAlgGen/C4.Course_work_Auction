package com.example.c4_course_work_auction.DTO;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class FullLot {
    private Integer id;
    private Status status;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    private Integer currentPrice;
    private Bid lastBid;

    public FullLot(Integer id,
                   Status status,
                   String title,
                   String description,
                   Integer startPrice,
                   Integer bidPrice,
                   Integer currentPrice,
                   String bidderName,
                   Instant bidDateTime) {
        this.id = id;
        this.status = status;
        this.title = title;
        this.description = description;
        this.startPrice = startPrice;
        this.bidPrice = bidPrice;
        this.currentPrice = currentPrice;
        this.lastBid = new Bid(bidderName, bidDateTime);
    }
}
