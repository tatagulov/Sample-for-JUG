package io.tatagulov.goodproject.web.api.repo;

import io.tatagulov.goodproject.web.api.Context;
import io.tatagulov.goodproject.web.api.ParamProvider;

import java.util.Map;

public interface SelectOneRepository {
    Map<String, String> selectOne(ParamProvider keyParamProvider, Context context);
}
