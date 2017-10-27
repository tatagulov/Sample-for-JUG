package io.tatagulov.badproject.web.service.impl;

import io.tatagulov.badproject.web.dto.flight.FlightDTO;
import io.tatagulov.badproject.web.dto.flight.FlightListDTO;
import io.tatagulov.badproject.web.dto.flight.FlightListRequest;
import io.tatagulov.badproject.web.entity.AircraftsEntity;
import io.tatagulov.badproject.web.entity.AirportsEntity;
import io.tatagulov.badproject.web.entity.FlightsEntity;
import io.tatagulov.badproject.web.mapper.FlightMapper;
import io.tatagulov.badproject.web.repo.AircraftRepository;
import io.tatagulov.badproject.web.repo.AirportRepository;
import io.tatagulov.badproject.web.repo.FlightRepository;
import io.tatagulov.badproject.web.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.tatagulov.badproject.web.util.Utils.validateNull;
import static java.util.stream.Collectors.toList;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final AirportRepository airportRepository;
    private final AircraftRepository aircraftRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository,
                             FlightMapper flightMapper,
                             AirportRepository airportRepository,
                             AircraftRepository aircraftRepository) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.airportRepository = airportRepository;
        this.aircraftRepository = aircraftRepository;
    }

    @Override
    public void delete(Integer flightId) {
        FlightsEntity flightsEntity = flightRepository.findById(flightId);
        if (flightsEntity!=null) flightRepository.delete(flightsEntity);
    }

    @Override
    public void updateStatus(Integer flightId, String status) {
        FlightsEntity flightsEntity = flightRepository.findById(flightId);
        if (flightsEntity!=null) {
            flightsEntity.setStatus(status);
            flightRepository.save(flightsEntity);
        }
    }

    @Override
    public void save(FlightDTO flightDTO) {
        validate(flightDTO);
        FlightsEntity flightsEntity;
        if (flightDTO.getFlightId()==null) {
            flightsEntity = new FlightsEntity();
        } else {
            flightsEntity = flightRepository.findById(flightDTO.getFlightId());
        }
        AirportsEntity departureAirport = airportRepository.findById(flightDTO.getDepartureAirport());
        AirportsEntity arrivalAirport = airportRepository.findById(flightDTO.getArrivalAirport());
        AircraftsEntity aircraftsEntity = aircraftRepository.findById(flightDTO.getAircraftCode());

        flightsEntity.setFlightNo(flightDTO.getFlightNo());
        flightsEntity.setScheduledDeparture(flightDTO.getScheduledDeparture());
        flightsEntity.setScheduledArrival(flightDTO.getScheduledArrival());
        flightsEntity.setAirportsByDepartureAirport(departureAirport);
        flightsEntity.setAirportsByDepartureAirport(arrivalAirport);
        flightsEntity.setStatus(flightDTO.getStatus());
        flightsEntity.setAircraftsByAircraftCode(aircraftsEntity);
        flightsEntity.setActualDeparture(flightDTO.getActualDeparture());
        flightsEntity.setActualArrival(flightDTO.getActualArrival());
        flightRepository.save(flightsEntity);
    }

    private void validate(FlightDTO flightDTO) {
        validateNull(flightDTO.getAircraftCode(),"AircraftCode");
        validateNull(flightDTO.getArrivalAirport(),"ArrivalAirport");
        validateNull(flightDTO.getDepartureAirport(),"DepartureAirport");
        validateNull(flightDTO.getFlightNo(),"FlightNo");
        validateNull(flightDTO.getScheduledArrival(),"ScheduledArrival");
        validateNull(flightDTO.getScheduledDeparture(),"ScheduledDeparture");
        validateNull(flightDTO.getStatus(),"Status");
    }

    @Override
    public List<FlightListDTO> getList(FlightListRequest flightListRequest) {
        List<FlightsEntity> entities = flightRepository.list(flightListRequest);
        return entities.stream().map(flightMapper::entityToListDTO).collect(toList());
    }

    @Override
    public FlightDTO selectOne(Integer flightId) {
        FlightsEntity entity = flightRepository.findById(flightId);
        return flightMapper.entityToDTO(entity);
    }
}
