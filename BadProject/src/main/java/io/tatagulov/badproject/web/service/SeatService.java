package io.tatagulov.badproject.web.service;

import io.tatagulov.badproject.web.dto.seat.SeatDTO;
import io.tatagulov.badproject.web.dto.seat.SeatListDTO;
import io.tatagulov.badproject.web.dto.seat.SeatListRequest;

import java.util.List;

public interface SeatService {
    void save(SeatDTO seatDTO);

    void delete(String aircraftCode, String seatNo);

    List<SeatListDTO> getList(SeatListRequest seatListRequest);

    SeatDTO selectOne(String aircraftCode, String seatNo);
}
