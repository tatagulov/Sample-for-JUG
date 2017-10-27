package io.tatagulov.badproject.web.service.impl;

import io.tatagulov.badproject.web.dto.seat.SeatDTO;
import io.tatagulov.badproject.web.dto.seat.SeatListDTO;
import io.tatagulov.badproject.web.dto.seat.SeatListRequest;
import io.tatagulov.badproject.web.entity.SeatsEntity;
import io.tatagulov.badproject.web.entity.SeatsEntityPK;
import io.tatagulov.badproject.web.mapper.SeatMapper;
import io.tatagulov.badproject.web.repo.SeatRepository;
import io.tatagulov.badproject.web.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final SeatMapper seatMapper;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository, SeatMapper seatMapper) {
        this.seatRepository = seatRepository;
        this.seatMapper = seatMapper;
    }

    @Override
    public void save(SeatDTO seatDTO) {
        SeatsEntityPK pk = new SeatsEntityPK();
        pk.setAircraftCode(seatDTO.getAircraftCode());
        pk.setSeatNo(seatDTO.getSeatNo());

        SeatsEntity seatsEntity = seatRepository.findById(pk);
        if (seatsEntity==null) {
            seatsEntity = new SeatsEntity();
            seatsEntity.setAircraftCode(seatDTO.getAircraftCode());
            seatsEntity.setSeatNo(seatDTO.getSeatNo());
        }
        seatsEntity.setFareConditions(seatDTO.getFareConditions());
        seatRepository.save(seatsEntity);
    }

    @Override
    public void delete(String aircraftCode, String seatNo) {
        SeatsEntityPK pk = new SeatsEntityPK();
        pk.setAircraftCode(aircraftCode);
        pk.setSeatNo(seatNo);
        SeatsEntity seatsEntity = seatRepository.findById(pk);
        if (seatsEntity!=null) {
            seatRepository.delete(seatsEntity);
        }
    }

    @Override
    public List<SeatListDTO> getList(SeatListRequest seatListRequest) {
        List<SeatsEntity> list = seatRepository.list(seatListRequest);
        return list.stream().map(seatMapper::entityToListDTO).collect(toList());
    }

    @Override
    public SeatDTO selectOne(String aircraftCode, String seatNo) {
        SeatsEntityPK pk = new SeatsEntityPK();
        pk.setAircraftCode(aircraftCode);
        pk.setSeatNo(seatNo);
        SeatsEntity seatsEntity = seatRepository.findById(pk);
        return seatMapper.entityToDTO(seatsEntity);
    }
}
