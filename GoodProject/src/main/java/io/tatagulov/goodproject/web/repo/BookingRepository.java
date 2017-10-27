package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.Select;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import io.tatagulov.goodproject.web.service.DefaultCRUDRepository;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import kz.inessoft.sud.interview.dao.entity.db.bookings.Bookings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static io.github.tatagulov.jsqb.core.sql.PostgresFunction.count;

@Repository
public class BookingRepository extends DefaultCRUDRepository<Bookings> implements SelectTableData {

    @Autowired
    public BookingRepository(JDBCHelper jdbcHelper) {
        super(jdbcHelper, Bookings::new);
    }

    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        Bookings bookings = new Bookings();

        select.select(bookings.book_date);
        select.select(bookings.book_ref);
        select.select(bookings.total_amount);
        select.select(count(bookings.innerTickets().ticket_no).as("ticket_cnt"));

        filterBuilder.filter(false,bookings.book_date, Criteria.GT_EQ,"start_date");
        filterBuilder.filter(false,bookings.book_date, Criteria.LS_EQ,"end_date");
        filterBuilder.filter(false,bookings.total_amount, Criteria.LS_EQ);
    }
}
