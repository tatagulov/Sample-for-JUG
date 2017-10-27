package io.tatagulov.badproject.web.service;

import io.tatagulov.badproject.web.dto.flight.FlightDTO;
import io.tatagulov.badproject.web.dto.flight.FlightListDTO;
import io.tatagulov.badproject.web.dto.flight.FlightListRequest;

import java.util.List;

public interface FlightService {
    void delete(Integer flightId);

    void updateStatus(Integer flightId, String status);

    void save(FlightDTO flightDTO);

    List<FlightListDTO> getList(FlightListRequest flightListRequest);

    FlightDTO selectOne(Integer flightId);
}
