package com.example.c4_course_work_auction.Mapper;

import com.example.c4_course_work_auction.DTO.CSVFile;
import com.example.c4_course_work_auction.DTO.CSVView;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class CVSMapper {
    @Contract("_ -> new")
    public  @NotNull CSVFile toCVS(@NotNull CSVView csvView){
        return new CSVFile(
                csvView.getId(),
                csvView.getTitle(),
                csvView.getStatus(),
                csvView.getName(),
                csvView.getCurrentPrice()
        );
    }

}
