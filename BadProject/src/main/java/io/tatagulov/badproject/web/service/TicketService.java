package io.tatagulov.badproject.web.service;

import io.tatagulov.badproject.web.dto.ticket.TicketDTO;
import io.tatagulov.badproject.web.dto.ticket.TicketListDTO;
import io.tatagulov.badproject.web.dto.ticket.TicketListRequest;

import java.util.List;

public interface TicketService {
    void save(TicketDTO ticketDTO);

    void delete(String ticketNo);

    List<TicketListDTO> getList(TicketListRequest ticketListRequest);

    TicketDTO selectOne(String ticketNo);
}
