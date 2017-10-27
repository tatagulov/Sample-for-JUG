package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.Select;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import io.tatagulov.goodproject.web.service.DefaultCRUDRepository;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import kz.inessoft.sud.interview.dao.entity.db.bookings.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository extends DefaultCRUDRepository<Tickets> implements SelectTableData {

    @Autowired
    public TicketRepository(JDBCHelper jdbcHelper) {
        super(jdbcHelper, Tickets::new);
    }

    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        Tickets tickets = new Tickets();
        select.select(tickets.ticket_no);
        select.select(tickets.book_ref);
        select.select(tickets.passenger_id);
        select.select(tickets.passenger_name);
        select.select(tickets.innerTicketFlights().innerFlights().flight_no);
        select.select(tickets.innerTicketFlights().innerFlights().scheduled_departure);
        select.select(tickets.innerTicketFlights().innerFlights().innerAircrafts().model);

        filterBuilder.filter(false,tickets.ticket_no, Criteria.EQ);
        filterBuilder.filter(false,tickets.passenger_name, Criteria.EQ);
        filterBuilder.filter(false,tickets.passenger_id, Criteria.EQ);
        filterBuilder.filter(false,tickets.book_ref, Criteria.EQ);
    }
}
