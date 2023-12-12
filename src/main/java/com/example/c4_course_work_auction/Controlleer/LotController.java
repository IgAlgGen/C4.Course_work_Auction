package com.example.c4_course_work_auction.Controlleer;

import com.example.c4_course_work_auction.DTO.*;
import com.example.c4_course_work_auction.Service.LotService;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/lot")
public class LotController {
    private final LotService lotService;

    public LotController(LotService lotService) {
        this.lotService = lotService;
    }


    @GetMapping("/{id}/first")
    public Bid getFirstBidder(@PathVariable int id) {
        return lotService.getFirstBidder(id);
    }

    @GetMapping("/{id}/frequent")
    public Bid getMostFrequentBidder(@PathVariable int id) {
        return lotService.getMostFrequentBidder(id);
    }

    @GetMapping("/{id}")
    public FullLot getFullLot(@PathVariable int id) {
        return lotService.getFullLot(id);
    }

    @PostMapping("/{id}/start")
    public void startBidding(@PathVariable int id) {
        lotService.startBidding(id);
    }

    @PostMapping("/{id}/bid")
    public void createBid(@PathVariable int id, @RequestBody @Valid Bidder bidder) {
        lotService.createBid(id, bidder);
    }

    @PostMapping("/{id}/stop")
    public void stopBidding(@PathVariable int id) {
        lotService.stopBidding(id);
    }

    @PostMapping()
    public Lot createLot(@RequestBody @Valid CreateLot createLot) {
        return lotService.createLot(createLot);
    }

    @GetMapping
    public List<Lot> findLots(@RequestParam(value = "status", required = false) Status status,
                              @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        return lotService.findLots(status, page);
    }

    @GetMapping(value = "/export")
    public ResponseEntity<Resource> getCSVFile() throws IOException {
        String fileName = "AllLots.cvs";
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CSVFile.class)
                .withNullValue("null")
                .withColumnSeparator(',')
                .withoutQuoteChar()
                .withHeader();
        ObjectWriter writer = mapper.writer(schema);
        Resource resource = new ByteArrayResource(writer.writeValueAsBytes(lotService.getCSVFile()));
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/csv")
                .body(resource);
    }
}
