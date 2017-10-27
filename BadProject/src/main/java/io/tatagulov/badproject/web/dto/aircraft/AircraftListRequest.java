package io.tatagulov.badproject.web.dto.aircraft;

import io.tatagulov.badproject.web.dto.BaseListRequest;

public class AircraftListRequest extends BaseListRequest {

    private String code;
    private Integer rangeStart;
    private Integer rangeEnd;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(Integer rangeStart) {
        this.rangeStart = rangeStart;
    }

    public Integer getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(Integer rangeEnd) {
        this.rangeEnd = rangeEnd;
    }
}
