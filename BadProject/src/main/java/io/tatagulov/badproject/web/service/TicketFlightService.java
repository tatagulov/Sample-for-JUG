package io.tatagulov.badproject.web.service;

import io.tatagulov.badproject.web.dto.ticketflight.TicketFlightDTO;
import io.tatagulov.badproject.web.dto.ticketflight.TicketFlightListRequest;

import java.util.List;

public interface TicketFlightService {
    void save(TicketFlightDTO ticketFlightDTO);

    void delete(Integer flightId, String ticketNo);

    List<TicketFlightDTO> getList(TicketFlightListRequest ticketFlightListRequest);

    TicketFlightDTO selectOne(Integer flightId, String ticketNo);
}
