package io.tatagulov.badproject.web.controller;

import io.tatagulov.badproject.web.dto.booking.BookingDTO;
import io.tatagulov.badproject.web.dto.booking.BookingListDTO;
import io.tatagulov.badproject.web.dto.booking.BookingListRequest;
import io.tatagulov.badproject.web.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/booking")
@Transactional
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping("/selectOne")
    public BookingDTO selectOne(@RequestParam String bookRef) {
        return bookingService.selectOne(bookRef);
    }

    @RequestMapping("/save")
    public void save(@RequestBody BookingDTO bookingDTO) {
        bookingService.save(bookingDTO);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam String bookRef) {
        bookingService.delete(bookRef);
    }

    @RequestMapping("/list")
    public List<BookingListDTO> list(@RequestBody BookingListRequest aircraftListRequest) {
        return bookingService.getList(aircraftListRequest);
    }
}
