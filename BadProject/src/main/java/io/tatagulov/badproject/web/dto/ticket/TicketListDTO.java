package io.tatagulov.badproject.web.dto.ticket;

import java.sql.Timestamp;

public class TicketListDTO {
    private String ticketNo;
    private String bookRef;
    private String passengerId;
    private String passengerName;
    private String flightNo;
    private Timestamp scheduledDeparture;
    private String model;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getBookRef() {
        return bookRef;
    }

    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
