package com.example.c4_course_work_auction.DTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Setter
@Getter
@JsonPropertyOrder ({"id","title","status","name","currentPrice"})
public class CSVFile {
   private Integer id;
   private String title;
   private Status status;
   private String name;
   private Integer currentPrice;

}
