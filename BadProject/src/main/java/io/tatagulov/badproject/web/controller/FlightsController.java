package io.tatagulov.badproject.web.controller;

import io.tatagulov.badproject.web.dto.flight.FlightDTO;
import io.tatagulov.badproject.web.dto.flight.FlightListDTO;
import io.tatagulov.badproject.web.dto.flight.FlightListRequest;
import io.tatagulov.badproject.web.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/flights")
@Transactional
public class FlightsController {

    private final FlightService flightService;

    @Autowired
    public FlightsController(FlightService flightService) {
        this.flightService = flightService;
    }

    @RequestMapping("/selectOne")
    public FlightDTO selectOne(@RequestParam Integer flightId) {
        return flightService.selectOne(flightId);
    }

    @RequestMapping("/updateStatus")
    public void updateStatus(@RequestParam Integer flightId,
                             @RequestParam String status) {
        flightService.updateStatus(flightId,status);
    }

    @RequestMapping("/save")
    public void save(@RequestBody FlightDTO flightDTO) {
        flightService.save(flightDTO);
    }

    @RequestMapping("/delete")
    public void delete(@RequestParam Integer flightId) {
        flightService.delete(flightId);
    }

    @RequestMapping("/list")
    public List<FlightListDTO> list(@RequestBody FlightListRequest flightListRequest) {
        return flightService.getList(flightListRequest);
    }
}
