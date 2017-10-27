package io.tatagulov.badproject.web.service.impl;

import io.tatagulov.badproject.web.dto.booking.BookingDTO;
import io.tatagulov.badproject.web.dto.booking.BookingListDTO;
import io.tatagulov.badproject.web.dto.booking.BookingListRequest;
import io.tatagulov.badproject.web.entity.BookingsEntity;
import io.tatagulov.badproject.web.mapper.BookingMapper;
import io.tatagulov.badproject.web.repo.BookingRepository;
import io.tatagulov.badproject.web.service.BookingService;
import io.tatagulov.badproject.web.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    @Override
    public void save(BookingDTO bookingDTO) {
        validate(bookingDTO);
        BookingsEntity bookingsEntity = bookingRepository.findById(bookingDTO.getBookRef());
        if (bookingsEntity==null) {
            bookingsEntity = new BookingsEntity();
            bookingsEntity.setBookRef(bookingDTO.getBookRef());
        }
        bookingsEntity.setBookDate(bookingDTO.getBookDate());
        bookingsEntity.setTotalAmount(bookingDTO.getTotalAmount());
        bookingRepository.save(bookingsEntity);
    }

    private void validate(BookingDTO bookingDTO) {
        Utils.validateNull(bookingDTO.getBookDate(),"BookDate");
        Utils.validateNull(bookingDTO.getBookRef(),"BookRef");
        Utils.validateNull(bookingDTO.getTotalAmount(),"TotalAmount");
    }

    @Override
    public void delete(String bookRef) {
        BookingsEntity bookingsEntity = bookingRepository.findById(bookRef);
        if (bookingsEntity!=null) {
            bookingRepository.delete(bookingsEntity);
        }
    }

    @Override
    public List<BookingListDTO> getList(BookingListRequest bookingListRequest) {
        List<BookingsEntity> list = bookingRepository.list(bookingListRequest);
        return list.stream().map(bookingMapper::entityToListDTO).collect(toList());
    }

    @Override
    public BookingDTO selectOne(String bookRef) {
        BookingsEntity bookingsEntity = bookingRepository.findById(bookRef);
        return bookingMapper.entityToDTO(bookingsEntity);
    }
}
