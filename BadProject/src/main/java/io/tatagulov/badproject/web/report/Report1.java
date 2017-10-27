package io.tatagulov.badproject.web.report;

import io.tatagulov.badproject.web.dto.report1.Report1Data;
import io.tatagulov.badproject.web.entity.FlightsEntity;
import io.tatagulov.badproject.web.entity.TicketFlightsEntity;
import io.tatagulov.badproject.web.repo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
public class Report1 {

    private final FlightRepository flightRepository;

    @Autowired
    public Report1(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Report1Data> getData() {
        List<FlightsEntity> all = flightRepository.listJoinFetchTicketFlight();
        Map<Integer,Long> result = new HashMap<>();
        for (FlightsEntity flightsEntity : all) {
            Collection<TicketFlightsEntity> ticketFlights = flightsEntity.getTicketFlightsByFlightId();
            Timestamp scheduledArrival = flightsEntity.getScheduledArrival();
            int monthValue = scheduledArrival.toLocalDateTime().getMonthValue();
            Long value = result.computeIfAbsent(monthValue, k -> 0L);
            for (TicketFlightsEntity ticketFlight : ticketFlights) {
                value += ticketFlight.getAmount().longValue();
            }
            result.put(monthValue,value);
        }
        List<Report1Data> list = new LinkedList<>();

        for (Integer integer : result.keySet()) {
            Report1Data report1Data1 = new Report1Data();
            report1Data1.setMonth(integer);
            report1Data1.setTotalAmount(result.get(integer));
            list.add(report1Data1);
        }
        return list;
    }
}
