package io.tatagulov.badproject.web.service;

import io.tatagulov.badproject.web.dto.booking.BookingDTO;
import io.tatagulov.badproject.web.dto.booking.BookingListDTO;
import io.tatagulov.badproject.web.dto.booking.BookingListRequest;

import java.util.List;

public interface BookingService {
    void save(BookingDTO bookingDTO);

    void delete(String bookRef);

    List<BookingListDTO> getList(BookingListRequest bookingListRequest);

    BookingDTO selectOne(String bookRef);
}
