package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.Select;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import io.tatagulov.goodproject.web.service.DefaultCRUDRepository;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import kz.inessoft.sud.interview.dao.entity.db.bookings.Seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SeatsRepository extends DefaultCRUDRepository<Seats> implements SelectTableData {

    @Autowired
    public SeatsRepository(JDBCHelper jdbcHelper) {
        super(jdbcHelper, Seats::new);
    }

    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        Seats seats = new Seats();
        select.select(seats.aircraft_code);
        select.select(seats.innerAircrafts().model);
        select.select(seats.seat_no);
        select.select(seats.fare_conditions);

        filterBuilder.filter(false,seats.aircraft_code, Criteria.EQ);
        filterBuilder.filter(false,seats.fare_conditions, Criteria.EQ);
    }
}
