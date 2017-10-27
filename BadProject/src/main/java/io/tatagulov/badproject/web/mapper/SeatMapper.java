package io.tatagulov.badproject.web.mapper;

import io.tatagulov.badproject.web.dto.seat.SeatDTO;
import io.tatagulov.badproject.web.dto.seat.SeatListDTO;
import io.tatagulov.badproject.web.entity.SeatsEntity;
import org.springframework.stereotype.Service;

@Service
public class SeatMapper {
    public SeatDTO entityToDTO(SeatsEntity seatsEntity) {
        SeatDTO dto = new SeatDTO();
        dto.setAircraftCode(seatsEntity.getAircraftCode());
        dto.setFareConditions(seatsEntity.getFareConditions());
        dto.setSeatNo(seatsEntity.getSeatNo());
        return dto;
    }

    public SeatListDTO entityToListDTO(SeatsEntity entity) {
        SeatListDTO dto = new SeatListDTO();
        dto.setAircraftCode(entity.getAircraftCode());
        dto.setFareConditions(entity.getFareConditions());
        dto.setSeatNo(entity.getSeatNo());
        dto.setModel(entity.getAircraftsByAircraftCode().getModel());
        return dto;
    }
}
