package io.tatagulov.goodproject.web.api;


import io.github.tatagulov.jsqb.core.sql.ParamExpression;
import io.github.tatagulov.jsqb.core.sql.PostgresFunction;
import io.github.tatagulov.jsqb.core.sql.SQLColumn;
import io.github.tatagulov.jsqb.core.sql.condition.Criteria;
import io.github.tatagulov.jsqb.core.sql.condition.TwoExpressionCondition;

public class FilterBuilder {

    private final ParamProvider paramProvider;

    public FilterBuilder(ParamProvider paramProvider) {
        this.paramProvider = paramProvider;
    }

    public <T> void filter(boolean required, SQLColumn<?,T> sqlColumn, Criteria criteria) {
        String paramName = sqlColumn.column.columnName;
        filter(required, sqlColumn, criteria, paramName);
    }

    public <T> void filter(boolean required, SQLColumn<?,T> expression, Criteria criteria, String paramName) {
        T value = paramProvider.get(required, paramName, expression.getType());
        if (value!=null) {
            ParamExpression<T> paramExpression = new ParamExpression<T>(value, expression.getType());
            expression.dbTable.where(new TwoExpressionCondition<T>(expression,criteria,paramExpression));
        }
    }

    public  void startWithIgnoreCase(boolean required,SQLColumn<?,String> sqlColumn) {
        startWithIgnoreCase(required,sqlColumn,sqlColumn.column.columnName);
    }

    public  void startWithIgnoreCase(boolean required,SQLColumn<?,String> sqlColumn,String paramName) {
        String value = paramProvider.get(required, paramName,sqlColumn.getType());
        if (value!=null) {
            sqlColumn.getSelectTable().where(new TwoExpressionCondition<>(PostgresFunction.upper(sqlColumn), Criteria.LIKE, ParamExpression.param(value.toUpperCase() + "%")));
        }
    }
}