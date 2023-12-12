package com.example.c4_course_work_auction.DTO;

import java.time.Instant;


public interface FullLotView {
    Integer getId();
    Status getStatus();
    String getTitle();
    String getDescription();
    Integer getStart_price();
    Integer getBid_price();
    Integer getCurrentPrice();
    String getName();
    Instant getDate_time();
}
