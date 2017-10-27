package io.tatagulov.goodproject.web.api.repo;

import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.ParamProvider;

public interface UpdateRepository {
    int update(ParamProvider keyParamProvider, ParamProvider updateParamProvider, Context context);
}
