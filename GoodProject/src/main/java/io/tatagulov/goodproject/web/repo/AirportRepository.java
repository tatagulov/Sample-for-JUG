package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.Select;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import io.tatagulov.goodproject.web.service.DefaultCRUDRepository;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import kz.inessoft.sud.interview.dao.entity.db.bookings.Airports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AirportRepository extends DefaultCRUDRepository<Airports> implements SelectTableData{

    @Autowired
    public AirportRepository(JDBCHelper jdbcHelper) {
        super(jdbcHelper, Airports::new);
    }

    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        Airports airports = new Airports();
        select.select(airports.airport_code);
        select.select(airports.airport_name);
        select.select(airports.city);
        select.select(airports.latitude);
        select.select(airports.longitude);
        select.select(airports.timezone);

        filterBuilder.filter(false,airports.airport_code, Criteria.EQ);
        filterBuilder.startWithIgnoreCase(false,airports.airport_name);
        filterBuilder.startWithIgnoreCase(false,airports.city);
    }
}
