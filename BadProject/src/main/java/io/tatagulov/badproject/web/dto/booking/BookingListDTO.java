package io.tatagulov.badproject.web.dto.booking;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BookingListDTO {
    private String bookRef;
    private Timestamp bookDate;
    private BigDecimal totalAmount;
    private Integer ticketCount;

    public String getBookRef() {
        return bookRef;
    }

    public void setBookRef(String bookRef) {
        this.bookRef = bookRef;
    }

    public Timestamp getBookDate() {
        return bookDate;
    }

    public void setBookDate(Timestamp bookDate) {
        this.bookDate = bookDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }
}
