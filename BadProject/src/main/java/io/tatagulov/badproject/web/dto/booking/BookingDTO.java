package io.tatagulov.badproject.web.dto.booking;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BookingDTO {
    private String bookRef;
    private Timestamp bookDate;
    private BigDecimal totalAmount;

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
}
