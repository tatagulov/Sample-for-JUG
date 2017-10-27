package io.tatagulov.badproject.web.service;

import io.tatagulov.badproject.web.dto.aircraft.AircraftDTO;
import io.tatagulov.badproject.web.dto.aircraft.AircraftListRequest;

import java.util.List;

public interface AircraftService {
    void save(AircraftDTO aircraftDTO);

    void delete(String code);

    List<AircraftDTO> getList(AircraftListRequest aircraftListRequest);

    AircraftDTO selectOne(String aircraftCode);
}
