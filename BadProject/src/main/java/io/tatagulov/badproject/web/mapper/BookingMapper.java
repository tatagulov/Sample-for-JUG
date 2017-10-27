package io.tatagulov.badproject.web.mapper;

import io.tatagulov.badproject.web.dto.booking.BookingDTO;
import io.tatagulov.badproject.web.dto.booking.BookingListDTO;
import io.tatagulov.badproject.web.entity.BookingsEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper {

    public BookingDTO entityToDTO(BookingsEntity entity) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookDate(entity.getBookDate());
        bookingDTO.setBookRef(entity.getBookRef());
        bookingDTO.setTotalAmount(entity.getTotalAmount());
        return bookingDTO;
    }

    public BookingListDTO entityToListDTO(BookingsEntity entity) {
        BookingListDTO dto = new BookingListDTO();
        dto.setBookDate(entity.getBookDate());
        dto.setBookRef(entity.getBookRef());
        dto.setTotalAmount(entity.getTotalAmount());
        dto.setTicketCount(entity.getTicketsByBookRef().size());
        return dto;
    }
}
