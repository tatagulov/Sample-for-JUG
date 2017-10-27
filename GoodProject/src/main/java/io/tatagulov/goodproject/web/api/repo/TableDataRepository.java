package io.tatagulov.goodproject.web.api.repo;

import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.ParamProvider;
import io.tatagulov.goodproject.web.api.dto.TableData;

public interface TableDataRepository {
    TableData getTableData(ParamProvider paramProvider, long page, long pageSize, Context context);
}
