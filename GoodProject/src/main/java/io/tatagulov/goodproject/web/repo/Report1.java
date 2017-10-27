package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.PostgresFunction;
import io.github.tatagulov.jsqb.core.sql.Select;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import kz.inessoft.sud.interview.dao.entity.db.bookings.Flights;
import org.springframework.stereotype.Repository;

import static io.github.tatagulov.jsqb.core.sql.PostgresFunction.*;

@Repository
public class Report1 implements SelectTableData {
    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        Flights flights = new Flights();
        select.select(month(flights.scheduled_arrival).as("month"));
        select.select(sum(flights.innerTicketFlights().amount,Long.class).as("total_amount"));
    }
}
