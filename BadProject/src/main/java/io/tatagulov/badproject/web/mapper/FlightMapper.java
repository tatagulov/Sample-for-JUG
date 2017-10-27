package io.tatagulov.badproject.web.mapper;

import io.tatagulov.badproject.web.dto.flight.FlightDTO;
import io.tatagulov.badproject.web.dto.flight.FlightListDTO;
import io.tatagulov.badproject.web.entity.FlightsEntity;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {

    public FlightListDTO entityToListDTO(FlightsEntity entity) {
        FlightListDTO dto = new FlightListDTO();
        dto.setFlightNo(entity.getFlightNo());
        dto.setScheduledDeparture(entity.getScheduledDeparture());
        dto.setScheduledArrival(entity.getScheduledArrival());
        dto.setStatus(entity.getStatus());
        dto.setActualDeparture(entity.getActualDeparture());
        dto.setActualArrival(entity.getActualArrival());
        dto.setArrivalAirportName(entity.getAirportsByArrivalAirport().getAirportName());
        dto.setArrivalCity(entity.getAirportsByArrivalAirport().getCity());
        dto.setDepartureAirportName(entity.getAirportsByDepartureAirport().getAirportName());
        dto.setDepartureCity(entity.getAirportsByDepartureAirport().getCity());
        dto.setAircraftCode(entity.getAircraftsByAircraftCode().getAircraftCode());
        dto.setModel(entity.getAircraftsByAircraftCode().getModel());
        return dto;
    }

    public FlightDTO entityToDTO(FlightsEntity entity) {
        FlightDTO dto = new FlightDTO();
        dto.setFlightId(entity.getFlightId());
        dto.setFlightNo(entity.getFlightNo());
        dto.setScheduledDeparture(entity.getScheduledDeparture());
        dto.setScheduledArrival(entity.getScheduledArrival());
        dto.setStatus(entity.getStatus());
        dto.setActualDeparture(entity.getActualDeparture());
        dto.setActualArrival(entity.getActualArrival());
        dto.setArrivalAirport(entity.getAirportsByArrivalAirport().getAirportCode());
        dto.setDepartureAirport(entity.getAirportsByDepartureAirport().getAirportCode());
        dto.setAircraftCode(entity.getAircraftsByAircraftCode().getAircraftCode());
        return dto;
    }
}
