package io.tatagulov.badproject.web.mapper;

import io.tatagulov.badproject.web.dto.ticket.TicketDTO;
import io.tatagulov.badproject.web.dto.ticket.TicketListDTO;
import io.tatagulov.badproject.web.entity.AircraftsEntity;
import io.tatagulov.badproject.web.entity.FlightsEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntity;
import io.tatagulov.badproject.web.entity.TicketsEntity;
import org.springframework.stereotype.Service;

@Service
public class TicketMapper {

    public TicketDTO entityToDTO(TicketsEntity entity) {
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketNo(entity.getTicketNo());
        ticketDTO.setBookRef(entity.getBookRef());
        ticketDTO.setPassengerId(entity.getPassengerId());
        ticketDTO.setPassengerName(entity.getPassengerName());
        return ticketDTO;
    }

    public TicketListDTO entityToListDTO(TicketsEntity entity) {
        TicketFlightsEntity ticketFlightsEntity = entity.getTicketFlightsByTicketNo().iterator().next();
        FlightsEntity flight = ticketFlightsEntity.getFlightsByFlightId();
        AircraftsEntity aircraft = flight.getAircraftsByAircraftCode();

        TicketListDTO dto = new TicketListDTO();
        dto.setTicketNo(entity.getTicketNo());
        dto.setBookRef(entity.getBookRef());
        dto.setPassengerId(entity.getPassengerId());
        dto.setPassengerName(entity.getPassengerName());
        dto.setFlightNo(flight.getFlightNo());
        dto.setScheduledDeparture(flight.getScheduledDeparture());
        dto.setModel(aircraft.getModel());
        return dto;
    }
}
