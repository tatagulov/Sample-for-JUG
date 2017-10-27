package io.tatagulov.badproject.web.mapper;

import io.tatagulov.badproject.web.dto.airport.AirportDTO;
import io.tatagulov.badproject.web.entity.AirportsEntity;
import org.springframework.stereotype.Service;

@Service
public class AirportMapper {

    public AirportDTO entityToDTO(AirportsEntity entity) {
        AirportDTO airportDTO = new AirportDTO();
        airportDTO.setAirportCode(entity.getAirportCode());
        airportDTO.setAirportName(entity.getAirportName());
        airportDTO.setCity(entity.getCity());
        airportDTO.setLatitude(entity.getLatitude());
        airportDTO.setLongitude(entity.getLongitude());
        airportDTO.setTimezone(entity.getTimezone());
        return airportDTO;
    }
}
