package com.example.c4_course_work_auction.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.generator.EventType;
import java.time.Instant;


@Entity
@Table (name = "bids")
@Getter
@Setter
@NoArgsConstructor
public class BidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CurrentTimestamp(event = EventType.INSERT, source = SourceType.DB)
    @Column(name = "date_time", nullable = false, updatable = false)
    private Instant dateTime;

    @Column (nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lot_id", nullable = false)
    private LotEntity lotEntity;

    public BidEntity(String name) {
        this.name = name;
    }

}