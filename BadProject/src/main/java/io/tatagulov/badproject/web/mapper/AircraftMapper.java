package io.tatagulov.badproject.web.mapper;

import io.tatagulov.badproject.web.dto.aircraft.AircraftDTO;
import io.tatagulov.badproject.web.entity.AircraftsEntity;
import org.springframework.stereotype.Service;

@Service
public class AircraftMapper {

    public AircraftDTO entityToDTO(AircraftsEntity aircraftEntity) {
        AircraftDTO dto = new AircraftDTO();
        dto.setAircraftCode(aircraftEntity.getAircraftCode());
        dto.setModel(aircraftEntity.getModel());
        dto.setRange(aircraftEntity.getRange());
        dto.setDescription(aircraftEntity.getDescription());
        return dto;
    }
}
