package com.example.c4_course_work_auction.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lot {
    private Integer id;
    private String title;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    private Status status;

}
