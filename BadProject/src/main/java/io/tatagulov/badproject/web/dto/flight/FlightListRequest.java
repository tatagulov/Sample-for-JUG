package io.tatagulov.badproject.web.dto.flight;

import io.tatagulov.badproject.web.dto.BaseListRequest;

public class FlightListRequest extends BaseListRequest {
    private String flightNo;
    private String status;
    private String departureAirport;
    private String arrivalAirport;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
}
