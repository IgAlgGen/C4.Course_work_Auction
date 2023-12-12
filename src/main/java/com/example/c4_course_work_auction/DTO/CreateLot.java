package com.example.c4_course_work_auction.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateLot {
    @NotBlank
    @Size(min = 3, max = 64)
    private String title;
    @NotBlank
    @Size(min = 1, max = 4096)
    private String description;
    @NotNull
    @PositiveOrZero
    @Min(1)
    private int startPrice;
    @NotNull
    @Positive
    @Min(1)
    private int bidPrice;

}