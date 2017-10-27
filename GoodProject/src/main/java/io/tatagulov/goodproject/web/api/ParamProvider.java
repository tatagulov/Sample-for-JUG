package io.tatagulov.goodproject.web.api;


import io.github.tatagulov.jsqb.core.sql.SQLColumn;

public interface ParamProvider {
    <T> T get(String paramName, Class<T> type, T defaultValue);
    <T> T get(boolean required, String paramName, Class<T> type);
    <T> T get(boolean required, SQLColumn<?, T> selectColumn);
    void set(String paramName, Object value);
    <T> void set(SQLColumn<?, T> sqlColumn, T value);
    void remove(String paramName);
    void remove(SQLColumn sqlColumn);
    boolean contains(String paramName);
}
