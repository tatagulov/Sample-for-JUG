package io.tatagulov.badproject.web.dto.seat;

import io.tatagulov.badproject.web.dto.BaseListRequest;

public class SeatListRequest extends BaseListRequest {
    private String aircraftCode;
    private String fareConditions;

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public String getFareConditions() {
        return fareConditions;
    }

    public void setFareConditions(String fareConditions) {
        this.fareConditions = fareConditions;
    }
}
