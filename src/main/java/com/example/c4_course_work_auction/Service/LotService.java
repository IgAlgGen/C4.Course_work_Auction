package com.example.c4_course_work_auction.Service;

import com.example.c4_course_work_auction.DTO.*;
import com.example.c4_course_work_auction.Entity.BidEntity;
import com.example.c4_course_work_auction.Entity.LotEntity;
import com.example.c4_course_work_auction.Exeptions.LotNotFoundException;
import com.example.c4_course_work_auction.Exeptions.LotNotStartedException;
import com.example.c4_course_work_auction.Mapper.BidMapper;
import com.example.c4_course_work_auction.Mapper.CVSMapper;
import com.example.c4_course_work_auction.Mapper.LotMapper;
import com.example.c4_course_work_auction.Repository.BidRepository;
import com.example.c4_course_work_auction.Repository.LotRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LotService {
    private final LotRepository lotRepository;
    private final BidRepository bidRepository;
    private final LotMapper lotMapper;
    private final BidMapper bidMapper;
    private final CVSMapper cvsMapper;

    public LotService(LotRepository lotRepository, BidRepository bidRepository, LotMapper lotMapper, BidMapper bidMapper, CVSMapper cvsMapper) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
        this.lotMapper = lotMapper;
        this.bidMapper = bidMapper;
        this.cvsMapper = cvsMapper;
    }

    public Lot createLot(CreateLot createLot) {
        return lotMapper.toDTO(lotRepository.save(lotMapper.toEntity(createLot)));
    }

    public void startBidding(int id) {
        Status status = Status.STARTED;
        LotEntity lotEntity = getLotEntity(id, status);
        lotRepository.save(lotEntity);
    }

    public void stopBidding(int id) {
        Status status = Status.STOPPED;
        LotEntity lotEntity = getLotEntity(id, status);
        lotRepository.save(lotEntity);
    }

    @NotNull
    private LotEntity getLotEntity(int id, Status status) {
        LotEntity lotEntity = lotRepository.findById(id).orElseThrow(LotNotFoundException::new);
        lotEntity.setStatus(status);
        return lotEntity;
    }

    public void createBid(int id, Bidder bidder) {
        LotEntity lotEntity = lotRepository.findById(id).orElseThrow(LotNotFoundException::new);
        if (lotEntity.getStatus() == Status.CREATED || lotEntity.getStatus() == Status.STOPPED) {
            throw new LotNotStartedException();
        } else if (lotEntity.getStatus() == Status.STARTED) {
            BidEntity bidEntity = new BidEntity();
            bidEntity.setName(bidder.getName());
            bidEntity.setLotEntity(lotEntity);
            bidRepository.save(bidEntity);
        }
    }

    public Bid getFirstBidder(int id) {
        return bidRepository.findFirstByLotEntity_IdOrderByDateTimeAsc(id).map(bidMapper::toDTO).orElseThrow(LotNotFoundException::new);
    }

    public Bid getMostFrequentBidder(int id) {
        return bidRepository.searchTheMostFrequentBidder(id).orElseThrow(LotNotFoundException::new);
    }

    public FullLot getFullLot(int id) {
        return lotRepository.getFullLot(id).map(lotMapper::toDTO).orElseThrow(LotNotFoundException::new);
    }

    public List<Lot> findLots(@Nullable Status status, int page) {
        Pageable lotsPage = PageRequest.of(Objects.requireNonNullElse(page, 0), 10);
        Page<LotEntity> tempLotsPage = lotRepository.findAllByStatus(status, lotsPage);
        return tempLotsPage.stream().map(lotMapper::toDTO).toList();
    }

    public List<CSVFile> getCSVFile() {
        return lotRepository.getAllLotsToCSV().stream().map(cvsMapper::toCVS).toList();
    }
}
