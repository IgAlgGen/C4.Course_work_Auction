package com.example.c4_course_work_auction.Entity;

import com.example.c4_course_work_auction.DTO.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "lots")
@Getter
@Setter

public class LotEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 4096, nullable = false)
    private String description;

    @Column(name = "start_price")
    private int startPrice;

    @Column(name = "bid_price")
    private int bidPrice;

    @Enumerated(EnumType.STRING)
    private Status status;

}
