package io.tatagulov.badproject.web.controller;


import io.tatagulov.badproject.web.dto.seat.SeatDTO;
import io.tatagulov.badproject.web.dto.seat.SeatListDTO;
import io.tatagulov.badproject.web.dto.seat.SeatListRequest;
import io.tatagulov.badproject.web.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("seat")
@Transactional
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @RequestMapping("/selectOne")
    public SeatDTO selectOne(@RequestParam String aircraftCode,
                             @RequestParam String seatNo) {
        return seatService.selectOne(aircraftCode,seatNo);
    }

    @RequestMapping("/save")
    public void save(@RequestBody SeatDTO seatDTO) {
        seatService.save(seatDTO);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam String aircraftCode,
                       @RequestParam String seatNo) {
        seatService.delete(aircraftCode,seatNo);
    }

    @RequestMapping("/list")
    public List<SeatListDTO> list(@RequestBody SeatListRequest seatListRequest) {
        return seatService.getList(seatListRequest);
    }
}
