package io.tatagulov.badproject.web.mapper;

import io.tatagulov.badproject.web.dto.ticketflight.TicketFlightDTO;
import io.tatagulov.badproject.web.entity.TicketFlightsEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketFlightMapper {
    public TicketFlightDTO entityToDTO(TicketFlightsEntity entity) {
        TicketFlightDTO dto = new TicketFlightDTO();
        dto.setFlightId(entity.getFlightId());
        dto.setAmount(entity.getAmount());
        dto.setTicketNo(entity.getTicketNo());
        dto.setFareConditions(entity.getFareConditions());
        return dto;
    }
}
