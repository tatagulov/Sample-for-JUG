package io.tatagulov.goodproject.web.repo;

import io.github.tatagulov.jsqb.core.sql.Select;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import io.tatagulov.goodproject.web.service.DefaultCRUDRepository;
import io.tatagulov.goodproject.web.service.JDBCHelper;
import kz.inessoft.sud.interview.dao.entity.db.bookings.Aircrafts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AircraftRepository extends DefaultCRUDRepository<Aircrafts> implements SelectTableData {

    @Autowired
    public AircraftRepository(JDBCHelper jdbcHelper) {
        super(jdbcHelper, Aircrafts::new);
    }

    @Override
    public void selectTableData(Select select, FilterBuilder filterBuilder, Context context) {
        Aircrafts aircrafts = new Aircrafts();

        select.select(aircrafts.aircraft_code);
        select.select(aircrafts.model);
        select.select(aircrafts.range);

        filterBuilder.filter(true,aircrafts.aircraft_code, Criteria.EQ);
        filterBuilder.filter(true,aircrafts.range, Criteria.GT_EQ,"range_start");
        filterBuilder.filter(true,aircrafts.range, Criteria.LS_EQ,"range_end");
    }
}
