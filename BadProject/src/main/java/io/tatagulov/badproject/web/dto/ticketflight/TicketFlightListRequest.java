package io.tatagulov.badproject.web.dto.ticketflight;

import io.tatagulov.badproject.web.dto.BaseListRequest;

public class TicketFlightListRequest extends BaseListRequest {
    private String ticketNo;
    private Integer flightId;

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }
}
