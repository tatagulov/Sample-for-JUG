package io.tatagulov.badproject.web.dto.airport;

import io.tatagulov.badproject.web.dto.BaseListRequest;

public class AirportListRequest extends BaseListRequest {

    private String airportCode;
    private String airportStartName;
    private String cityStartName;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportStartName() {
        return airportStartName;
    }

    public void setAirportStartName(String airportStartName) {
        this.airportStartName = airportStartName;
    }


    public String getCityStartName() {
        return cityStartName;
    }

    public void setCityStartName(String cityStartName) {
        this.cityStartName = cityStartName;
    }
}
