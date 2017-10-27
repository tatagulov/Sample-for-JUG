package io.tatagulov.goodproject.web.api.repo;

import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.ParamProvider;

public interface DeleteRepository {
    int delete(ParamProvider keyParamProvider, Context context);
}
