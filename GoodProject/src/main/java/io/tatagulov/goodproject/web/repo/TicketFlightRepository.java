package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.Select;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import io.tatagulov.goodproject.web.service.DefaultCRUDRepository;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import kz.inessoft.sud.interview.dao.entity.db.bookings.TicketFlights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketFlightRepository extends DefaultCRUDRepository<TicketFlights> implements SelectTableData{

    @Autowired
    public TicketFlightRepository(JDBCHelper jdbcHelper) {
        super(jdbcHelper, TicketFlights::new);
    }

    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        TicketFlights ticketFlights = new TicketFlights();
        select.select(ticketFlights.ticket_no);
        select.select(ticketFlights.amount);
        select.select(ticketFlights.fare_conditions);
        select.select(ticketFlights.ticket_no);

        filterBuilder.filter(false,ticketFlights.ticket_no, Criteria.EQ);
        filterBuilder.filter(false,ticketFlights.flight_id, Criteria.EQ);
    }
}
