package io.tatagulov.badproject.web.service.impl;

import io.tatagulov.badproject.web.dto.ticketflight.TicketFlightDTO;
import io.tatagulov.badproject.web.dto.ticketflight.TicketFlightListRequest;
import io.tatagulov.badproject.web.entity.TicketFlightsEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntityPK;
import io.tatagulov.badproject.web.mapper.TicketFlightMapper;
import io.tatagulov.badproject.web.repo.TicketFlightRepository;
import io.tatagulov.badproject.web.service.TicketFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TicketFlightServiceImpl implements TicketFlightService {
    private final TicketFlightRepository ticketFlightRepository;
    private final TicketFlightMapper ticketFlightMapper;

    @Autowired
    public TicketFlightServiceImpl(TicketFlightRepository ticketFlightRepository, TicketFlightMapper ticketFlightMapper) {
        this.ticketFlightRepository = ticketFlightRepository;
        this.ticketFlightMapper = ticketFlightMapper;
    }

    @Override
    public void save(TicketFlightDTO ticketFlightDTO) {
        TicketFlightsEntityPK pk = new TicketFlightsEntityPK();
        pk.setFlightId(ticketFlightDTO.getFlightId());
        pk.setTicketNo(ticketFlightDTO.getTicketNo());

        TicketFlightsEntity ticketFlightsEntity = ticketFlightRepository.findById(pk);
        if (ticketFlightsEntity==null) {
            ticketFlightsEntity = new TicketFlightsEntity();
            ticketFlightsEntity.setFlightId(ticketFlightDTO.getFlightId());
            ticketFlightsEntity.setTicketNo(ticketFlightDTO.getTicketNo());
        }
        ticketFlightsEntity.setFareConditions(ticketFlightDTO.getFareConditions());
        ticketFlightRepository.save(ticketFlightsEntity);
    }

    @Override
    public void delete(Integer flightId, String ticketNo) {
        TicketFlightsEntityPK pk = new TicketFlightsEntityPK();
        pk.setFlightId(flightId);
        pk.setTicketNo(ticketNo);
        TicketFlightsEntity ticketFlightsEntity = ticketFlightRepository.findById(pk);
        if (ticketFlightsEntity!=null) {
            ticketFlightRepository.delete(ticketFlightsEntity);
        }
    }

    @Override
    public List<TicketFlightDTO> getList(TicketFlightListRequest ticketFlightListRequest) {
        List<TicketFlightsEntity> list = ticketFlightRepository.list(ticketFlightListRequest);
        return list.stream().map(ticketFlightMapper::entityToDTO).collect(toList());
    }


    @Override
    public TicketFlightDTO selectOne(Integer flightId, String ticketNo) {
        TicketFlightsEntityPK pk = new TicketFlightsEntityPK();
        pk.setFlightId(flightId);
        pk.setTicketNo(ticketNo);
        TicketFlightsEntity ticketFlightsEntity = ticketFlightRepository.findById(pk);
        return ticketFlightMapper.entityToDTO(ticketFlightsEntity);
    }
}

