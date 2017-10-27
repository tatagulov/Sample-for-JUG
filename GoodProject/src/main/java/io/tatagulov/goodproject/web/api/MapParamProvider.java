package io.tatagulov.goodproject.web.api;

import io.github.tatagulov.jsqb.core.sql.SQLColumn;

import java.util.HashMap;
import java.util.Map;

public class MapParamProvider implements ParamProvider {

    private final Map<String, String> map;

    public MapParamProvider(Map<String,String> map) {
        this.map = (map == null ? new HashMap<>() : map);
    }

    @Override
    public <T> T get(String paramName, Class<T> type, T defaultValue) {
        T value = Converter.getObjectValue(map, paramName, type);
        return value==null ? defaultValue : value;
    }

    @Override
    public <T> T get(boolean required, String paramName, Class<T> type) {
        T value = Converter.getObjectValue(map, paramName, type);
        if (value==null && required) {
            String message = String.format("param \"%s\" can't be null",paramName);
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    @Override
    public <T> T get(boolean required, SQLColumn<?, T> selectColumn) {
        return get(required,selectColumn.column.columnName,selectColumn.getType());
    }

    @Override
    public void set(String paramName, Object value) {
        map.put(paramName,Converter.getStringValue(value));
    }

    @Override
    public <T> void set(SQLColumn<?, T> sqlColumn, T value) {
        map.put(sqlColumn.column.columnName,Converter.getStringValue(value));
    }

    @Override
    public void remove(String paramName) {
        map.remove(paramName);
    }

    @Override
    public void remove(SQLColumn sqlColumn) {
        map.remove(sqlColumn.column.columnName);
    }

    @Override
    public boolean contains(String paramName) {
        return map.containsKey(paramName);
    }
}