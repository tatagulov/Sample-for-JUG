package io.tatagulov.badproject.web.dto.flight;

import java.sql.Timestamp;

public class FlightListDTO {
    private String flightNo;
    private Timestamp scheduledDeparture;
    private Timestamp scheduledArrival;
    private String status;
    private Timestamp actualDeparture;
    private Timestamp actualArrival;
    private String model;
    private String aircraftCode ;
    private String arrivalAirportName;
    private String departureAirportName;
    private String arrivalCity;
    private String departureCity;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public Timestamp getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(Timestamp scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public Timestamp getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(Timestamp scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getActualDeparture() {
        return actualDeparture;
    }

    public void setActualDeparture(Timestamp actualDeparture) {
        this.actualDeparture = actualDeparture;
    }

    public Timestamp getActualArrival() {
        return actualArrival;
    }

    public void setActualArrival(Timestamp actualArrival) {
        this.actualArrival = actualArrival;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public void setArrivalAirportName(String arrivalAirportName) {
        this.arrivalAirportName = arrivalAirportName;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }
}
