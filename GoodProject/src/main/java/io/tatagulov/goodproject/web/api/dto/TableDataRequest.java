package io.tatagulov.goodproject.web.api.dto;

import java.util.HashMap;
import java.util.Map;

public class TableDataRequest {
    public Long page = 1L;
    public Long pageSize = 10L;
    public Map<String,String> filter = new HashMap<>();
}
