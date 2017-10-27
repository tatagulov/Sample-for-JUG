package io.tatagulov.badproject.web.service.impl;

import io.tatagulov.badproject.web.dto.ticket.TicketDTO;
import io.tatagulov.badproject.web.dto.ticket.TicketListDTO;
import io.tatagulov.badproject.web.dto.ticket.TicketListRequest;
import io.tatagulov.badproject.web.entity.TicketsEntity;
import io.tatagulov.badproject.web.mapper.TicketMapper;
import io.tatagulov.badproject.web.repo.TicketRepository;
import io.tatagulov.badproject.web.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public void save(TicketDTO ticketDTO) {
        TicketsEntity ticketsEntity = ticketRepository.findById(ticketDTO.getTicketNo());
        if (ticketsEntity==null) {
            ticketsEntity = new TicketsEntity();
            ticketsEntity.setTicketNo(ticketDTO.getTicketNo());
        }
        ticketsEntity.setBookRef(ticketDTO.getBookRef());
        ticketsEntity.setPassengerId(ticketDTO.getPassengerId());
        ticketsEntity.setPassengerName(ticketDTO.getPassengerName());
        ticketRepository.save(ticketsEntity);
    }

    @Override
    public void delete(String ticketNo) {
        TicketsEntity ticketsEntity = ticketRepository.findById(ticketNo);
        if (ticketsEntity!=null) {
            ticketRepository.delete(ticketsEntity);
        }
    }

    @Override
    public List<TicketListDTO> getList(TicketListRequest ticketListRequest) {
        List<TicketsEntity> list = ticketRepository.list(ticketListRequest);
        return list.stream().map(ticketMapper::entityToListDTO).collect(toList());
    }

    @Override
    public TicketDTO selectOne(String ticketNo) {
        TicketsEntity ticketsEntity = ticketRepository.findById(ticketNo);
        return ticketMapper.entityToDTO(ticketsEntity);
    }
}
