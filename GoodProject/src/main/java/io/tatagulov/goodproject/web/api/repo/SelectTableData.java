package io.tatagulov.goodproject.web.api.repo;

import io.github.tatagulov.jsqb.core.sql.Select;
import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.FilterBuilder;

public interface SelectTableData {
    void selectTableData(Select select, FilterBuilder filterBuilder, Context context);
}
