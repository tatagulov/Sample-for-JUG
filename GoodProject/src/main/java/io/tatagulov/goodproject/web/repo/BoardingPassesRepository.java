package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.Select;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import io.tatagulov.goodproject.web.service.DefaultCRUDRepository;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import kz.inessoft.sud.interview.dao.entity.db.bookings.BoardingPasses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardingPassesRepository extends DefaultCRUDRepository<BoardingPasses> implements SelectTableData {

    @Autowired
    public BoardingPassesRepository(JDBCHelper jdbcHelper) {
        super(jdbcHelper, BoardingPasses::new);
    }

    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        BoardingPasses boardingPasses = new BoardingPasses();
        select.select(boardingPasses.ticket_no);
        select.select(boardingPasses.flight_id);
        select.select(boardingPasses.innerTickets().passenger_name);
        select.select(boardingPasses.innerFlights().flight_no);
        select.select(boardingPasses.seat_no);

        filterBuilder.filter(false,boardingPasses.boarding_no, Criteria.EQ);
    }
}
