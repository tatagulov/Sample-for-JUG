package io.tatagulov.badproject.web.service;

import io.tatagulov.badproject.web.dto.airport.AirportDTO;
import io.tatagulov.badproject.web.dto.airport.AirportListRequest;

import java.util.List;

public interface AirportService {
    void save(AirportDTO aircraftDTO);

    void delete(String code);

    List<AirportDTO> getList(AirportListRequest request);

    AirportDTO findById(String code);

    AirportDTO selectOne(String airportCode);
}
