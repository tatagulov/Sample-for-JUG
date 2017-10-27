package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.OrderType;
import io.github.tatagulov.jsqb.core.sql.Select;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import io.tatagulov.goodproject.web.service.DefaultCRUDRepository;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import kz.inessoft.sud.interview.dao.entity.db.bookings.Flights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FlightRepository extends DefaultCRUDRepository<Flights> implements SelectTableData {

    @Autowired
    public FlightRepository(JDBCHelper jdbcHelper) {
        super(jdbcHelper, Flights::new);
    }

    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        Flights flights = new Flights();

        select.select(flights.flight_no);
        select.select(flights.innerAirportsByArrivalAirport().airport_name.as("arrival_airport_name"));
        select.select(flights.innerAirportsByDepartureAirport().airport_name.as("departure_airport_name"));
        select.select(flights.innerAircrafts().model);
        select.select(flights.scheduled_departure);
        select.select(flights.scheduled_arrival);
        select.select(flights.actual_departure);
        select.select(flights.actual_arrival);
        select.select(flights.status);

        select.orderBy(flights.flight_id, OrderType.DESC);

        filterBuilder.filter(false,flights.flight_no, Criteria.EQ);
        filterBuilder.filter(false,flights.status, Criteria.EQ);
        filterBuilder.filter(false,flights.arrival_airport, Criteria.EQ);
        filterBuilder.filter(false,flights.departure_airport, Criteria.EQ);
    }
}
