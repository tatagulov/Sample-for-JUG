package io.tatagulov.badproject.web.controller;

import io.tatagulov.badproject.web.dto.airport.AirportDTO;
import io.tatagulov.badproject.web.dto.airport.AirportListRequest;
import io.tatagulov.badproject.web.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/airport")
@Transactional
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @RequestMapping("/selectOne")
    public AirportDTO selectOne(String airportCode) {
        return airportService.selectOne(airportCode);
    }

    @RequestMapping("/save")
    public void save(@RequestBody AirportDTO airportDTO) {
        airportService.save(airportDTO);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam String airportCode) {
        airportService.delete(airportCode);
    }

    @RequestMapping("/list")
    public List<AirportDTO> list(@RequestBody AirportListRequest airportListRequest) {
        return airportService.getList(airportListRequest);
    }
}
