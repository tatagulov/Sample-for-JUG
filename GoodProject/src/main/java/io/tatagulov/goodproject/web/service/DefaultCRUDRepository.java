package io.tatagulov.goodproject.web.service;

import io.github.tatagulov.jsqb.core.sql.SQLTable;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.ParamProvider;
import io.tatagulov.goodproject.web.api.repo.CRUDRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public abstract class DefaultCRUDRepository<K extends SQLTable<K>> implements CRUDRepository {

    protected final JDBCHelper jdbcHelper;
    protected final TableCreator<K> tableCreator;

    public interface TableCreator<K extends SQLTable<K>> {
        K create();
    }

    public DefaultCRUDRepository(JDBCHelper jdbcHelper, TableCreator<K> tableCreator) {
        this.jdbcHelper = jdbcHelper;
        this.tableCreator = tableCreator;
    }

    @Override
    public int delete(ParamProvider keyParamProvider, Context context) {
        return jdbcHelper.delete(tableCreator.create(),keyParamProvider);
    }

    @Override
    public int insert(ParamProvider insertParamProvider, Context context) {
        return jdbcHelper.insert(tableCreator.create(),insertParamProvider);
    }

    @Override
    public int update(ParamProvider keyParamProvider, ParamProvider updateParamProvider, Context context) {
        return jdbcHelper.update(tableCreator.create(),keyParamProvider,updateParamProvider);
    }

    @Override
    public Map<String, String> selectOne(ParamProvider keyParamProvider, Context context) {
        return jdbcHelper.selectOne(tableCreator.create(),keyParamProvider);
    }
}
