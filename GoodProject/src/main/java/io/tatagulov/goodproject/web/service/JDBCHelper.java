package io.tatagulov.goodproject.web.service;

import io.github.tatagulov.jsqb.core.sql.*;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.Converter;
import io.tatagulov.goodproject.web.api.FilterBuilder;
import io.tatagulov.goodproject.web.api.ParamProvider;
import io.tatagulov.goodproject.web.api.dto.TableData;
import io.tatagulov.goodproject.web.api.repo.SelectTableData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class JDBCHelper {

    private final PostgresConverter postgresConverter = new PostgresConverter();
    private final MapRowMapper mapRowMapper = new MapRowMapper();
    private final JdbcTemplate jdbcTemplate;

    class MapRowMapper implements RowMapper<Map<String,String>> {
        public Map<String, String> mapRow(ResultSet rs, int index) throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();
            Map<String,String> map = new LinkedHashMap<>();
            for (int i=1;i<=metaData.getColumnCount();i++) {
                String columnLabel = metaData.getColumnLabel(i);
                Object objectValue = rs.getObject(i);
                String stringValue = Converter.getStringValue(objectValue);
                map.put(columnLabel,stringValue);
            }
            return map;
        }
    }

    @Autowired
    public JDBCHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public <K extends SQLTable<K>> int insert(K sqlTable, ParamProvider paramProvider) {
        Insert<K> insert = new Insert<>(sqlTable);
        sqlTable.sqlColumns.forEach(k->setValues(insert, k, paramProvider));
        SQLData sqlData = insert.getSQLData(postgresConverter);
        return jdbcTemplate.update(sqlData.sql, sqlData.values);
    }

    public <K extends SQLTable<K>> int update(K sqlTable, ParamProvider keyParamProvider, ParamProvider updateParamProvider) {
        Update<K> update = new Update<>(sqlTable);
        FilterBuilder filterBuilder = new FilterBuilder(keyParamProvider);
        sqlTable.sqlColumns.forEach(k->filterBuilder.filter(k.column.isPK, k, Criteria.EQ));
        sqlTable.sqlColumns.forEach(k->setValues(update,k,updateParamProvider));
        SQLData sqlData = update.getSQL(postgresConverter);
        return jdbcTemplate.update(sqlData.sql, sqlData.values);
    }

    private static <K extends SQLTable<K>, T> void setValues(Insert<K> insert, SQLColumn<K, T> sqlColumn, ParamProvider paramProvider) {
        T value = paramProvider.get(!sqlColumn.column.nullable, sqlColumn);
        if (value != null) insert.set(sqlColumn, value);
    }

    private static <K extends SQLTable<K>, T> void setValues(Update<K> update, SQLColumn<K, T> sqlColumn, ParamProvider paramProvider) {
        if (paramProvider.contains(sqlColumn.column.columnName)) {
            T value = paramProvider.get(!sqlColumn.column.nullable, sqlColumn);
            update.set(sqlColumn, value);
        }
    }

    public <K extends SQLTable<K>> int delete(K sqlTable, ParamProvider keyParamProvider) {
        FilterBuilder filterBuilder = new FilterBuilder(keyParamProvider);
        sqlTable.sqlColumns.forEach(k->filterBuilder.filter(k.column.isPK, k, Criteria.EQ));

        SQLData sqlData = new Delete<>(sqlTable).getSQLData(postgresConverter);

        return jdbcTemplate.update(sqlData.sql, sqlData.values);
    }

    public <K extends SQLTable<K>> Map<String, String> selectOne(K sqlTable, ParamProvider keyParamProvider) {
        FilterBuilder filterBuilder = new FilterBuilder(keyParamProvider);
        Select select = new Select();
        sqlTable.sqlColumns.forEach(select::select);
        sqlTable.sqlColumns.forEach(sqlColumn -> filterBuilder.filter(sqlColumn.column.isPK, sqlColumn, Criteria.EQ));

        SQLData sqlData = select.getSQLData(postgresConverter);
        return jdbcTemplate.queryForObject(sqlData.sql, sqlData.values, mapRowMapper);
    }

    public TableData getTableData(ParamProvider paramProvider, long page, long pageSize, SelectTableData selectTableData, Context context) {
        Select select = new Select();
        select.limit(pageSize);
        select.offset((page - 1) * pageSize);

        FilterBuilder filterBuilder = new FilterBuilder(paramProvider);
        selectTableData.selectTableData(select,filterBuilder,context);

        SQLData sqlData = select.getSQLData(postgresConverter);
        SQLData countSQLData = select.getCountSQL(postgresConverter);

        TableData tableData = new TableData();
        tableData.rows = jdbcTemplate.query(sqlData.sql, sqlData.values,mapRowMapper);
        tableData.count = jdbcTemplate.queryForObject(countSQLData.sql, countSQLData.values, Long.class);
        return tableData;
    }
}
