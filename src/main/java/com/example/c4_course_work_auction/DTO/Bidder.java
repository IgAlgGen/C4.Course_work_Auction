package com.example.c4_course_work_auction.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bidder {

    @NotBlank
    private String name;
}
