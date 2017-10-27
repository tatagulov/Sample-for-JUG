package io.tatagulov.badproject.web.service.impl;

import io.tatagulov.badproject.web.dto.airport.AirportDTO;
import io.tatagulov.badproject.web.dto.airport.AirportListRequest;
import io.tatagulov.badproject.web.entity.AirportsEntity;
import io.tatagulov.badproject.web.mapper.AirportMapper;
import io.tatagulov.badproject.web.repo.AirportRepository;
import io.tatagulov.badproject.web.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.tatagulov.badproject.web.util.Utils.validateNull;
import static java.util.stream.Collectors.toList;

@Service
public class AirportServiceIml implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Autowired
    public AirportServiceIml(AirportRepository airportRepository, AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    @Override
    public void save(AirportDTO aircraftDTO) {
        validate(aircraftDTO);
        AirportsEntity airportsEntity = airportRepository.findById(aircraftDTO.getAirportCode());
        if (airportsEntity == null) {
            airportsEntity = new AirportsEntity();
            airportsEntity.setAirportCode(aircraftDTO.getAirportCode());
        }
        airportsEntity.setAirportName(aircraftDTO.getAirportName());
        airportsEntity.setCity(aircraftDTO.getCity());
        airportsEntity.setLatitude(aircraftDTO.getLatitude());
        airportsEntity.setLongitude(aircraftDTO.getLongitude());
        airportsEntity.setTimezone(aircraftDTO.getTimezone());
        airportRepository.save(airportsEntity);
    }

    private void validate(AirportDTO aircraftDTO) {
        validateNull(aircraftDTO.getAirportCode(),"AirportCode");
        validateNull(aircraftDTO.getAirportName(),"AirportName");
        validateNull(aircraftDTO.getCity(),"City");
        validateNull(aircraftDTO.getLatitude(),"Latitude");
        validateNull(aircraftDTO.getLongitude(),"Longitude");
        validateNull(aircraftDTO.getTimezone(),"Timezone");
    }

    @Override
    public void delete(String code) {
        AirportsEntity aircraftEntity = airportRepository.findById(code);
        if (aircraftEntity!=null) {
            airportRepository.delete(aircraftEntity);
        }
    }

    @Override
    public List<AirportDTO> getList(AirportListRequest request) {
        List<AirportsEntity> list = airportRepository.getList(request);
        return list.stream().map(airportMapper::entityToDTO).collect(toList());
    }

    @Override
    public AirportDTO findById(String code) {
        AirportsEntity airportsEntity = airportRepository.findById(code);
        return airportMapper.entityToDTO(airportsEntity);
    }

    @Override
    public AirportDTO selectOne(String airportCode) {
        AirportsEntity entity = airportRepository.findById(airportCode);
        return airportMapper.entityToDTO(entity);
    }
}
